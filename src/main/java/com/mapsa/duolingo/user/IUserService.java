package com.mapsa.duolingo.user;

import java.util.List;

public interface IUserService {

    User save(User user);

    User getById(Long userId);

    List<User> getAll();

    void deleteById(Long userId);

    void changeLevel(Long userId);
}
