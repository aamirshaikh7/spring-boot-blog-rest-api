package com.demo.blogrestapi.exception;

public class BlogException extends RuntimeException {
    private final String message;

    public BlogException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
