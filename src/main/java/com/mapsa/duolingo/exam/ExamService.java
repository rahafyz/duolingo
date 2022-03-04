package com.mapsa.duolingo.exam;

import com.mapsa.duolingo.common.GenericRepository;
import com.mapsa.duolingo.common.GenericService;
import com.mapsa.duolingo.config.FileStorageProperties;
import com.mapsa.duolingo.exception.CustomException;
import com.mapsa.duolingo.exception.NotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    @Override
    public Resource loadFileExam(Long examId) {
        try {
            Path path = Path.of(getById(examId).getLocation());
            Resource exam = new UrlResource(path.toUri());
            if(exam.exists())
                return exam;
            else
                throw new NotFoundException("file not fount");
        }catch (MalformedURLException e){
            throw new NotFoundException("file not found"+ e.getCause());
        }
    }
}
