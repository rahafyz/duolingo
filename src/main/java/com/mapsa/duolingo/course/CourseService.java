package com.mapsa.duolingo.course;

import com.mapsa.duolingo.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course save(Course course) {
        return null;
    }

    @Override
    public Course getById(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);

        if (course.isEmpty())
            throw new NotFoundException("There is no course by this id");

        return course.get();
    }

    @Override
    public List<Course> getAll() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public void delete(Long courseId) {
       if(courseRepository.findById(courseId).isEmpty())
           throw new NotFoundException("There is no course by this id");

       courseRepository.deleteById(courseId);
    }
}
