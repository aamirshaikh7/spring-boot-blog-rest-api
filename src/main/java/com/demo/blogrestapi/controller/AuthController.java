package com.demo.blogrestapi.controller;

import com.demo.blogrestapi.model.Role;
import com.demo.blogrestapi.model.User;
import com.demo.blogrestapi.payload.JwtAuthDto;
import com.demo.blogrestapi.payload.SigninDto;
import com.demo.blogrestapi.payload.SignupDto;
import com.demo.blogrestapi.repository.RoleRepository;
import com.demo.blogrestapi.repository.UserRepository;
import com.demo.blogrestapi.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Api("AuthController exposes signin and signup Rest APIs")
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @ApiOperation("Rest API to authenticate and signin user")
    @PostMapping("signin")
    public ResponseEntity<JwtAuthDto> signin (@RequestBody SigninDto signinDto) {
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(signinDto.getUsernameOrEmail(), signinDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get toke from jwtTokenProvider

        String token = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthDto(token));
    }

    @ApiOperation("Rest API to register user")
    @PostMapping("signup")
    public ResponseEntity<?> signup (@RequestBody SignupDto signupDto) {
        if (userRepository.existsByUsername(signupDto.getUsername())) {
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signupDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(signupDto.getName());
        user.setUsername(signupDto.getUsername());
        user.setEmail(signupDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").orElse(null);
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
