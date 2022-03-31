package com.mapsa.duolingo.course;

import com.mapsa.duolingo.common.GenericRepository;
import com.mapsa.duolingo.common.GenericService;
import com.mapsa.duolingo.courseUser.CourseUser;
import com.mapsa.duolingo.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService extends GenericService<Course,Long> implements ICourseService {

    private CourseRepository courseRepository;

    public CourseService(GenericRepository<Course, Long> repository, CourseRepository courseRepository) {
        super(repository);
        this.courseRepository = courseRepository;
    }

    public List<Course> getByLang(Long langId){
        return courseRepository.findByLanguage(langId);
    }

    @Override
    public List<User> users(Course course) {
        List<User> users = course.getUsers().stream().map(CourseUser::getUser).toList();
        return users;
    }
}
