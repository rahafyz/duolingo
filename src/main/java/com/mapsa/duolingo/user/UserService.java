package com.mapsa.duolingo.user;

import com.mapsa.duolingo.common.GenericRepository;
import com.mapsa.duolingo.common.GenericService;
import com.mapsa.duolingo.course.Course;
import com.mapsa.duolingo.course.CourseService;
import com.mapsa.duolingo.courseUser.CourseUser;
import com.mapsa.duolingo.courseUser.CourseUserKey;
import com.mapsa.duolingo.courseUser.ICourseUserService;
import com.mapsa.duolingo.exception.ConflictException;
import com.mapsa.duolingo.exception.CustomException;
import com.mapsa.duolingo.exception.NotFoundException;
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
            throw new ConflictException("this user is already exist");
        return userRepository.save(user);
    }


    @Override
    public String login(String username, String password) {
        if(!authentication(username,password))
            throw new CustomException("Invalid password",HttpStatus.UNPROCESSABLE_ENTITY);
        return jwtBuilder.generateToken(userRepository.findUserByUserName(username).get());
    }

    @Override
    public void addCourse(Long courseId) {
        CourseUserKey courseUserKey = new CourseUserKey();
        courseUserKey.setCourseId(courseId);
        courseUserKey.setUserId(userDetail.getUserId());
    }

    private boolean authentication(String username, String password) {
        User currentUser = userRepository.findUserByUserName(username).orElseThrow(
                () -> new NotFoundException("user doesn't exist.")
        );
        return currentUser.getPassword().equals(password);
    }
}
