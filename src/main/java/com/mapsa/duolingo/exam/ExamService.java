package com.mapsa.duolingo.exam;

import com.mapsa.duolingo.common.GenericRepository;
import com.mapsa.duolingo.common.GenericService;
import com.mapsa.duolingo.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ExamService extends GenericService<Exam, Long> implements IExamService {

    private ExamRepository repository;

    public ExamService(GenericRepository<Exam, Long> repository, ExamRepository repository1) {
        super(repository);
        this.repository = repository1;
    }

    @Override
    public Exam getByLocation(String location) {
        return repository.findByLocation(location).orElseThrow(
                () -> {
                    throw new CustomException("the exam not found!", HttpStatus.NOT_FOUND);

                }
        );
    }
}
