package com.mapsa.duolingo.exam;

import com.mapsa.duolingo.common.IGenericService;

public interface IExamService extends IGenericService<Exam, Long> {

    Exam getByLocation(String location);
}
