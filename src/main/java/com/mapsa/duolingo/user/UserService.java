package com.mapsa.duolingo.user;

import com.mapsa.duolingo.common.GenericRepository;
import com.mapsa.duolingo.common.GenericService;
import com.mapsa.duolingo.course.Course;
import com.mapsa.duolingo.course.CourseService;
import com.mapsa.duolingo.courseUser.CourseUser;
import com.mapsa.duolingo.courseUser.CourseUserKey;
import com.mapsa.duolingo.courseUser.ICourseUserService;
import com.mapsa.duolingo.exception.CustomException;
import com.mapsa.duolingo.exception.NotFoundException;
import com.mapsa.duolingo.level.Level;
import com.mapsa.duolingo.security.JwtBuilder;
import com.mapsa.duolingo.security.UserDetail;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService extends GenericService<User, Long> implements IUserService {

    private final UserRepository userRepository;
    private final JwtBuilder jwtBuilder;
    private final ICourseUserService courseUserService;
    private final UserDetail userDetail;

    @Override
    protected GenericRepository<User, Long> getRepository() {
        return userRepository;
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

    @Override
    public User changeLevel(Long userId) {
        User user = getById(userId);
        Integer level = user.getLevel().getValue();
        if (level<5) {
            level +=1;
            Level newLevel = Level.of(level);
            user.setLevel(newLevel);
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public List<Course> getUserCourses(User user){
        List<Course> courses = user.getCourses().stream().map(CourseUser::getCourse).collect(Collectors.toList());
        return courses;
    }

    private boolean authentication(String username, String password) {
        User currentUser = userRepository.findUserByUserName(username).orElseThrow(
                () -> new NotFoundException("user doesn't exist!")
        );
        return currentUser.getPassword().equals(password);
    }

    @Override
    public List<User> findByCourseName(String courseName) {
        return userRepository.findByCourse_name(courseName);
    }
}
