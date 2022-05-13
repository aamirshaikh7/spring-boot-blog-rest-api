package com.demo.blogrestapi.payload;

import lombok.Data;

@Data
public class SigninDto {
    private String usernameOrEmail;

    private String password;
}
