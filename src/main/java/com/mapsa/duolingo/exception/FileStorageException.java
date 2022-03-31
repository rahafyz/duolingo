package com.mapsa.duolingo.exception;

import org.springframework.http.HttpStatus;

public class FileStorageException extends CustomException {
    public FileStorageException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message,HttpStatus.FORBIDDEN, cause);
    }
}
