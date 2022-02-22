package com.mapsa.duolingo.level;

import com.mapsa.duolingo.common.GenericRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends GenericRepository<Level, Long> {
}
