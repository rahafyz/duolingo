package com.mapsa.duolingo.course;


import com.mapsa.duolingo.common.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends GenericRepository<Course,Long> {
}