package com.mapsa.duolingo.user;

import com.mapsa.duolingo.common.GenericRepository;
import com.mapsa.duolingo.common.GenericService;
import com.mapsa.duolingo.course.CourseService;
import com.mapsa.duolingo.courseUser.CourseUserKey;
import com.mapsa.duolingo.courseUser.ICourseUserService;
import com.mapsa.duolingo.exception.CustomException;
import com.mapsa.duolingo.security.JwtBuilder;
import com.mapsa.duolingo.security.UserDetail;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class UserService extends GenericService<User, Long> implements IUserService {

    private UserRepository userRepository;
    private JwtBuilder jwtBuilder;
    private ICourseUserService courseUserService;
    private CourseService courseService;
    private UserDetail userDetail;

    public UserService(GenericRepository<User, Long> repository, UserRepository userRepository, JwtBuilder jwtBuilder, ICourseUserService courseUserService, CourseService courseService, UserDetail userDetail) {
        super(repository);
        this.userRepository = userRepository;
        this.jwtBuilder = jwtBuilder;
        this.courseUserService = courseUserService;
        this.courseService = courseService;
        this.userDetail = userDetail;
    }

    @Override
    public User save(User user) {

        if (userRepository.existsUserByEmailAddressOrUserName(user.getEmailAddress(), user.getPassword()))
            throw new CustomException("this user already exist!", HttpStatus.CONFLICT);
        return userRepository.save(user);
    }


    @Override
    public String login(String username, String password) {
        if (!authentication(username, password))
            throw new CustomException("Invalid password", HttpStatus.UNPROCESSABLE_ENTITY);
        return jwtBuilder.generateToken(userRepository.findUserByUserName(username).get());
    }

    @Override
    public void addCourse(Long courseId) {
        CourseUserKey courseUserKey = new CourseUserKey();
        courseUserKey.setCourseId(courseId);
        courseUserKey.setUserId(userDetail.getUserId());
        courseUserService.save(courseUserKey);
    }

    private boolean authentication(String username, String password) {
        User currentUser = userRepository.findUserByUserName(username).orElseThrow(
                () -> new CustomException("user doesn't exist!", HttpStatus.NOT_FOUND)
        );
        return currentUser.getPassword().equals(password);
    }
}
