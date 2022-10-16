package com.mapsa.duolingo.user;


import com.mapsa.duolingo.common.IGenericService;
import com.mapsa.duolingo.course.Course;

import java.util.List;


public interface IUserService extends IGenericService<User, Long> {

    User register(User user);
    void addCourse(Long courseId);
    User changeLevel(Long userId);
    List<Course> getUserCourses(User user);
    List<User> findByCourseName(String courseName);
    boolean authentication(String username, String password);
    User findByUserName(String username);
}
