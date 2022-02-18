package com.mapsa.duolingo.common;

import org.springframework.data.repository.PagingAndSortingRepository;


public interface GenericRepository<T extends BaseEntity,U> extends PagingAndSortingRepository<T,U> {
}
