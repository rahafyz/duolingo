package com.mapsa.duolingo.test;

import com.mapsa.duolingo.common.GenericRepository;
import com.mapsa.duolingo.exam.Exam;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends GenericRepository<Test,Long> {
}
