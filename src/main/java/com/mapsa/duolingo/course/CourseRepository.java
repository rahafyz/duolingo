package com.mapsa.duolingo.course;


import com.mapsa.duolingo.common.GenericRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends GenericRepository<Course, Long> {
    List<Course> getByLanguage_Id(Long langId);

    @Override
    @Cacheable(value = "courses")
    List<Course> findAll();

    @Override
    @Cacheable(value = "courses", key = "#p0")
    Optional<Course> findById(Long aLong);

    @Override
            @CacheEvict(value = "courses", allEntries = true)
    <S extends Course> S save(S entity);

    @Override
    @CacheEvict(value = "courses", key = "#p0")
    void deleteById(Long aLong);

}