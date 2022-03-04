package com.mapsa.duolingo.test;

import com.mapsa.duolingo.common.IGenericService;
import org.springframework.web.multipart.MultipartFile;

public interface ITestService extends IGenericService<Test,Long> {
    Test save(Long userId, Long examId);

    String storeFile(Long userId, Long examId, MultipartFile file);
}
