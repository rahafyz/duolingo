package com.mapsa.duolingo.security;

import com.mapsa.duolingo.exception.NotFoundException;
import com.mapsa.duolingo.user.User;
import com.mapsa.duolingo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetail {
    private UserRepository userRepository;

    @Autowired
    public JwtUserDetail(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loadByUsername(String username) throws NotFoundException{
        final User user = userRepository.findUserByUserName(username).orElseThrow(
                ()-> new NotFoundException("user doesn't exist.")
        );
        return user;
    }


}
