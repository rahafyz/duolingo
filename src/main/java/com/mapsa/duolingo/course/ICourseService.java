package com.mapsa.duolingo.course;


import com.mapsa.duolingo.common.IGenericService;
import com.mapsa.duolingo.user.User;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface ICourseService extends IGenericService<Course,Long> {
    List<Course> getByLang(Long langId);

    List<User> users(Long courseId);

}
