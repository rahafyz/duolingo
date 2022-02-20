package com.mapsa.duolingo.user;


import com.mapsa.duolingo.common.IGenericService;


public interface IUserService extends IGenericService<User, Long> {

    User save(User user);
    User login(User user);
}
