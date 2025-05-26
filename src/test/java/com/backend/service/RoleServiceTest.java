package com.backend.service;

import com.backend.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RoleServiceTest {
    @Autowired
    private RoleService roleService;

    @Test
    void testFindAll() {
        List<Role> roles = roleService.findAll();
        assertEquals(3, roles.size());
    }
}
