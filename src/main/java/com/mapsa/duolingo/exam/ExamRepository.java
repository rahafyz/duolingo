package com.mapsa.duolingo.exam;

import com.mapsa.duolingo.common.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends GenericRepository<Exam, Long> {
    Optional<Exam> findByLocation(String location);
}