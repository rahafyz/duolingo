package com.mapsa.duolingo.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{

    private final String message;
    private final HttpStatus httpStatus;
    private Throwable cause;

    public CustomException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public CustomException(String message, HttpStatus httpStatus, Throwable cause) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.cause = cause;

    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
