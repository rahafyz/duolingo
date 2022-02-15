package com.mapsa.duolingo.course;


import java.util.List;

public interface ICourseService {

    Course save(Course course);

    Course getById(Long courseId);

    List<Course> getAll();

    void delete(Long courseId);

}
