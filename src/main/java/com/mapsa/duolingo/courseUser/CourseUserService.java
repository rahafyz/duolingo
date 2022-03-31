package com.mapsa.duolingo.courseUser;

import com.mapsa.duolingo.course.Course;
import com.mapsa.duolingo.course.CourseService;
import com.mapsa.duolingo.user.User;
import com.mapsa.duolingo.user.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseUserService implements ICourseUserService {

    private CourseUserRepository repository;
    private CourseService courseService;
    private UserService userService;

    public CourseUserService(CourseUserRepository repository, @Lazy CourseService courseService,@Lazy UserService userService) {
        this.repository = repository;
        this.courseService = courseService;
        this.userService = userService;
    }


    @Override
    public CourseUser save(CourseUserKey id) {
        Course myCourse = courseService.getById(id.getCourseId());
        User user = userService.getById(id.getUserId());
        CourseUser newCourseUser = new CourseUser();
        newCourseUser.setId(id);
        newCourseUser.setUser(user);
        newCourseUser.setCourse(myCourse);
        return repository.save(newCourseUser);
    }
}
