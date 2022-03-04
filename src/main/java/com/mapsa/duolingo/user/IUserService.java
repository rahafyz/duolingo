package com.mapsa.duolingo.user;


import com.mapsa.duolingo.common.IGenericService;


public interface IUserService extends IGenericService<User, Long> {

    User save(User user);
    String login(String username, String password);
    void addCourse(Long courseId);
    User changeLevel(Long userId);
}
