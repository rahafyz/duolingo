package com.mapsa.duolingo.course;


import com.mapsa.duolingo.common.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends GenericRepository<Course,Long> {
    List<Course> findByLanguage(Long langId);
}