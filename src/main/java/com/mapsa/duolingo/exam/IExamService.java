package com.mapsa.duolingo.exam;

import com.mapsa.duolingo.common.IGenericService;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IExamService extends IGenericService<Exam, Long> {

    Exam getByLocation(String location);

    Resource loadFileExam(Long examId);

}
