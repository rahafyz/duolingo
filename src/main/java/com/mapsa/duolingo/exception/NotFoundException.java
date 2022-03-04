package com.mapsa.duolingo.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException{

    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, HttpStatus.NOT_FOUND, cause);
    }
}
