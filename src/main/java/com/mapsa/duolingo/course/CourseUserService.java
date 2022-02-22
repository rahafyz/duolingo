package com.mapsa.duolingo.course;

import com.mapsa.duolingo.courseUser.CourseUserRepository;
import com.mapsa.duolingo.courseUser.ICourseUserService;
import com.mapsa.duolingo.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseUserService implements ICourseUserService {

    private CourseUserRepository repository;

    public CourseUserService(CourseUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getUsersByCourse(Long courseId) {
        return repository.findByCourse_Id(courseId);
    }

    @Override
    public List<Course> getCourseByUser(Long userId) {
        return repository.findByUser_Id(userId);
    }
}
