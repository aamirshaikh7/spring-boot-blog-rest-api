package com.demo.blogrestapi.repository;

import com.demo.blogrestapi.model.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoleRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;

    @AfterEach
    void tearDown() {
        roleRepository.deleteAll();
    }

    @Test
    void findByName() {
        Role role = new Role();

        String name = "ROLE_ADMIN";

        role.setName(name);

        roleRepository.save(role);

        Optional<Role> exists = roleRepository.findByName(name);

        assertThat(exists).isPresent();
    }

    @Test
    void notPresentByName() {
        String name = "XYZ";

        Optional<Role> exists = roleRepository.findByName(name);

        assertThat(exists).isNotPresent();
    }
}