package com.mapsa.duolingo.common;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface GenericRepository<T extends BaseEntity,U> extends PagingAndSortingRepository<T,U> {
}
