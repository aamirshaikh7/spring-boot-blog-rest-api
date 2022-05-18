package com.demo.blogrestapi.repository;

import com.demo.blogrestapi.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void findByEmail() {
        String email = "abc@test.com";

        User user = new User();
        user.setName("Abc");
        user.setUsername("abc");
        user.setEmail(email);
        user.setPassword("Abc@Abc");

        userRepository.save(user);

        Optional<User> exists = userRepository.findByEmail(email);

        assertThat(exists).isPresent();
    }

    @Test
    void notPresentByEmail() {
        String email = "abc@test.com";

        Optional<User> exists = userRepository.findByEmail(email);

        assertThat(exists).isNotPresent();
    }

    @Test
    void findByUsernameOrEmail() {
        String username = "abc";
        String email = "abc@test.com";

        User user = new User();
        user.setName("Abc");
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword("Abc@Abc");

        userRepository.save(user);

        Optional<User> exists = userRepository.findByUsernameOrEmail(username, email);

        assertThat(exists).isPresent();
    }

    @Test
    void notPresentByUsernameOrEmail() {
        String username = "abc";
        String email = "abc@test.com";

        Optional<User> exists = userRepository.findByUsernameOrEmail(username, email);

        assertThat(exists).isNotPresent();
    }


    @Test
    void findByUsername() {
        String username = "abc";

        User user = new User();
        user.setName("Abc");
        user.setUsername(username);
        user.setEmail("abc@gmail.com");
        user.setPassword("Abc@Abc");

        userRepository.save(user);

        Optional<User> exists = userRepository.findByUsername(username);

        assertThat(exists).isPresent();
    }

    @Test
    void notPresentByUsername() {
        String username = "abc";

        Optional<User> exists = userRepository.findByUsername(username);

        assertThat(exists).isNotPresent();
    }


    @Test
    void existsByUsername() {
        String username = "abc";

        User user = new User();
        user.setName("Abc");
        user.setUsername(username);
        user.setEmail("abc@gmail.com");
        user.setPassword("Abc@Abc");

        userRepository.save(user);

        boolean exists = userRepository.existsByUsername(username);

        assertThat(exists).isTrue();
    }

    @Test
    void notExistsByUsername() {
        String username = "abc";

        boolean exists = userRepository.existsByUsername(username);

        assertThat(exists).isFalse();
    }

    @Test
    void existsByEmail() {
        String email = "abc@test.com";

        User user = new User();
        user.setName("Abc");
        user.setUsername("abc");
        user.setEmail(email);
        user.setPassword("Abc@Abc");

        userRepository.save(user);

        boolean exists = userRepository.existsByEmail(email);

        assertThat(exists).isTrue();
    }

    @Test
    void notExistsByEmail() {
        String email = "something@test.com";

        boolean exists = userRepository.existsByEmail(email);

        assertThat(exists).isFalse();
    }
}