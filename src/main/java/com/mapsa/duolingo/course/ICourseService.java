package com.mapsa.duolingo.course;


import com.mapsa.duolingo.common.IGenericService;

import java.util.List;

public interface ICourseService extends IGenericService<Course,Long> {
    public List<Course> getByLang(Long langId);

}
