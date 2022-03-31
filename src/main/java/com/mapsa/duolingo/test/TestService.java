package com.mapsa.duolingo.test;

import com.mapsa.duolingo.common.GenericRepository;
import com.mapsa.duolingo.common.GenericService;
import com.mapsa.duolingo.config.FileStorageProperties;
import com.mapsa.duolingo.exam.Exam;
import com.mapsa.duolingo.exam.IExamService;
import com.mapsa.duolingo.exception.FileStorageException;
import com.mapsa.duolingo.exception.NotFoundException;
import com.mapsa.duolingo.user.IUserService;
import com.mapsa.duolingo.user.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class TestService extends GenericService<Test,Long> implements ITestService {

    private TestRepository repository;
    private IExamService examService;
    private IUserService userService;
    private final Path fileStorageLocation;

    public TestService(GenericRepository<Test, Long> repository, TestRepository repository1, IExamService examService, @Lazy IUserService userService, FileStorageProperties properties) {
        super(repository);
        this.repository = repository1;
        this.examService = examService;
        this.userService = userService;
        this.fileStorageLocation = Paths.get(properties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new NotFoundException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public Test save(Long userId, Long examId) {
        Exam exam = examService.getById(examId);
        User user = userService.getById(userId);
        Test newTest = Test.builder()
                .exam(exam)
                .user(user)
                .mark(100D)
                .build();
        return repository.save(newTest);

    }

    @Override
    public String storeFile(Long userId, Long examId, MultipartFile file) {
        String fileName = userId+"_"+examId;
        try {
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        }catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }

    }
}
