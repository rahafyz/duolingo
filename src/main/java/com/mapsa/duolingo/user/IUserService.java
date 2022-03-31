package com.mapsa.duolingo.user;


import com.mapsa.duolingo.common.IGenericService;
import com.mapsa.duolingo.course.Course;

import java.util.List;


public interface IUserService extends IGenericService<User, Long> {

    User save(User user);
    String login(String username, String password);
    void addCourse(Long courseId);
    User changeLevel(Long userId);
    List<Course> getUserCourses(User user);
}
