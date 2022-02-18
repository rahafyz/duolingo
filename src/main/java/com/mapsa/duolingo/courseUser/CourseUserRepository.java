package com.mapsa.duolingo.courseUser;

import com.mapsa.duolingo.course.Course;
import com.mapsa.duolingo.user.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CourseUserRepository extends PagingAndSortingRepository<CourseUser, CourseUserKey> {
    List<Course> findByUser_Id(Long userId);
    List<User> findByCourse_Id(Long curseId);
}