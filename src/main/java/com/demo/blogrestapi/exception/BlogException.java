package com.demo.blogrestapi.exception;

import org.springframework.http.HttpStatus;

public class BlogException extends RuntimeException {
    private final HttpStatus status;

    private final String message;

    public BlogException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
