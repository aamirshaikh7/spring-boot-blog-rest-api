package com.demo.blogrestapi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Scanner;

public class PasswordEncoderGenerator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String password = sc.next();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode(password));
    }
}
