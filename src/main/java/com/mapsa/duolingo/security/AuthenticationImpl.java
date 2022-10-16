package com.mapsa.duolingo.security;

import com.mapsa.duolingo.exception.CustomException;
import com.mapsa.duolingo.rabbitMq.MessageDTO;
import com.mapsa.duolingo.rabbitMq.ProducerService;
import com.mapsa.duolingo.redis.RedisService;
import com.mapsa.duolingo.user.IUserService;
import com.mapsa.duolingo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthenticationImpl {

    private final IUserService userService;
    private final JwtBuilder jwtBuilder;
    private final ProducerService producerService;
    private final RedisService redisService;

    public String login(String username, String password) {
        if (!userService.authentication(username, password))
            throw new CustomException("Invalid password", HttpStatus.UNPROCESSABLE_ENTITY);
        jwtBuilder.generateToken(userService.findByUserName(username));
        return jwtBuilder.generateToken(userService.findByUserName(username));
    }

    public void sendVerificationCode(User user) {
        Random random = new Random();
        Integer code = random.nextInt(6);

        MessageDTO message = new MessageDTO()
                .emailAddress(user.getEmailAddress())
                .message(String.valueOf(code));
        producerService.sendMessage(message);

        redisService.setValue("auth-" + user.getUserName(), String.valueOf(code), TimeUnit.DAYS, 5);

    }

    public String verification(String username, String verificationCode) {
        String code = redisService.getValue("auth-" + username);
        if (verificationCode.equals(code))
            return jwtBuilder.generateToken(userService.findByUserName(username));
        throw new CustomException("invalid code", HttpStatus.FORBIDDEN);
    }
}
