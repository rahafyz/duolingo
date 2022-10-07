package com.mapsa.duolingo.common;

import com.mapsa.duolingo.course.Course;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface GenericRepository<T,U> extends PagingAndSortingRepository<T,U> {
}
