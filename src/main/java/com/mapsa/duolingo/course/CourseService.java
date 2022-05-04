package com.mapsa.duolingo.course;

import com.mapsa.duolingo.common.GenericRepository;
import com.mapsa.duolingo.common.GenericService;
import com.mapsa.duolingo.courseUser.CourseUser;
import com.mapsa.duolingo.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService extends GenericService<Course,Long> implements ICourseService {

    private CourseRepository courseRepository;

    @Override
    protected GenericRepository<Course, Long> getRepository() {
        return courseRepository;
    }

    public List<Course> getByLang(Long langId){
        return courseRepository.findByLanguage(langId);
    }

    @Override
    public List<User> users(Long courseId) {
        List<User> users = getById(courseId).getUsers().stream().map(CourseUser::getUser).toList();
        return users;
    }
}
