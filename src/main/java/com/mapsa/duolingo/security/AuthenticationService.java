package com.mapsa.duolingo.security;

import com.mapsa.duolingo.user.User;

public interface AuthenticationService {
//    String login(String username, String password);

    void sendVerificationCode(User user);

    String verification(String username, String verificationCode);
}
