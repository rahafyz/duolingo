package com.mapsa.duolingo.security;

import com.mapsa.duolingo.exception.CustomException;
import com.mapsa.duolingo.rabbitMq.MessageDTO;
import com.mapsa.duolingo.rabbitMq.ProducerService;
import com.mapsa.duolingo.user.IUserService;
import com.mapsa.duolingo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationImpl {

    private final IUserService userService;
    private final JwtBuilder jwtBuilder;
    private final ProducerService producerService;
    private final RedisTemplate<String,String> redisTemplate;

    public String login(String username, String password) {
        if (!userService.authentication(username, password))
            throw new CustomException("Invalid password", HttpStatus.UNPROCESSABLE_ENTITY);
        jwtBuilder.generateToken(userService.findByUserName(username));
        return jwtBuilder.generateToken(userService.findByUserName(username));
    }

    public void sendVerificationCode(User user){
        Random random = new Random();
        Integer code = random.nextInt(6);

        MessageDTO message = new MessageDTO()
                .emailAddress(user.getEmailAddress())
                .message(String.valueOf(code));
        producerService.sendMessage(message);

        ListOperations<String,String> verification = redisTemplate.opsForList();
        verification.rightPush(user.getUserName(),String.valueOf(code));

    }

    public String verification(String verificationCode){
        return null;
    }
}
