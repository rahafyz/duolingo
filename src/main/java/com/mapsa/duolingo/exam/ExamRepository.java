package com.mapsa.duolingo.exam;

import com.mapsa.duolingo.common.GenericRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExamRepository extends GenericRepository<Exam, Long> {
}