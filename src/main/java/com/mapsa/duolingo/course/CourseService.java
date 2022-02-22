package com.mapsa.duolingo.course;

import com.mapsa.duolingo.common.GenericRepository;
import com.mapsa.duolingo.common.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
