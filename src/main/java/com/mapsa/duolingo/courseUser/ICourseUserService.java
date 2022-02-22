package com.mapsa.duolingo.courseUser;

import com.mapsa.duolingo.course.Course;
import com.mapsa.duolingo.user.User;

import java.util.List;

public interface ICourseUserService{
    List<User> getUsersByCourse(Long courseId);
    List<Course> getCourseByUser(Long userId);
}
