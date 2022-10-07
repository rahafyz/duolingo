package com.mapsa.duolingo.course;

import com.mapsa.duolingo.common.GenericRepository;
import com.mapsa.duolingo.common.GenericService;
import com.mapsa.duolingo.courseUser.CourseUser;
import com.mapsa.duolingo.courseUser.CourseUserRepository;
import com.mapsa.duolingo.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService extends GenericService<Course,Long> implements ICourseService {

    private CourseRepository courseRepository;

    private CourseUserRepository courseUserRepository;

    @Override
    protected GenericRepository<Course, Long> getRepository() {
        return courseRepository;
    }

    public List<Course> getByLang(Long langId){
        return courseRepository.getByLanguage_Id(langId);
    }

    @Override
    @Transactional
    public List<User> users(Long courseId) {
        List<CourseUser> courseUsers = courseUserRepository.findByCourse_Id(courseId);
        return courseUsers.stream().map(CourseUser::getUser).collect(Collectors.toList());
    }
}
