package com.backend.service;

import com.backend.entity.User;
import com.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testFindAll() {
        List<User> users = userService.findAll();
        User user = users.getFirst();
        assertEquals(1, users.size());
        assertEquals("admin", user.getUsername());
        assertNotNull(user.getRole());
    }

    @Test
    void testSave() {
        User admin = new User();
        admin.setUsername("admin");

        User expected = new User();
        expected.setUsername("user");
        expected.setPassword(passwordEncoder.encode("user"));
        expected.setRole(roleService.findAll().getFirst());
        userService.save(expected);
        User actual = userRepository.findById(expected.getUsername()).orElseThrow();
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getRole(), actual.getRole());
        assertEquals(expected.isAccountNonExpired(), actual.isAccountNonExpired());
        assertEquals(expected.isAccountNonLocked(), actual.isAccountNonLocked());
        assertEquals(expected.isCredentialsNonExpired(), actual.isCredentialsNonExpired());
        assertEquals(expected.isEnabled(), actual.isEnabled());
    }

    @Test
    void testFind() {
        User expected = new User();
        expected.setUsername("user");
        expected.setPassword(passwordEncoder.encode("user"));
        expected.setRole(roleService.findAll().getFirst());
        userRepository.save(expected);
        User actual = userService.find(expected.getUsername());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getRole(), actual.getRole());
        assertEquals(expected.isAccountNonExpired(), actual.isAccountNonExpired());
        assertEquals(expected.isAccountNonLocked(), actual.isAccountNonLocked());
        assertEquals(expected.isCredentialsNonExpired(), actual.isCredentialsNonExpired());
        assertEquals(expected.isEnabled(), actual.isEnabled());
    }

    @Test
    void testUpdate() {
        User expected = new User();
        expected.setUsername("user");
        expected.setPassword(passwordEncoder.encode("user"));
        expected.setRole(roleService.findAll().getFirst());
        userRepository.save(expected);
        expected.setEnabled(false);
        User actual = userService.update(expected.getUsername(), expected);
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getRole(), actual.getRole());
        assertEquals(expected.isAccountNonExpired(), actual.isAccountNonExpired());
        assertEquals(expected.isAccountNonLocked(), actual.isAccountNonLocked());
        assertEquals(expected.isCredentialsNonExpired(), actual.isCredentialsNonExpired());
        assertEquals(expected.isEnabled(), actual.isEnabled());
    }

    @Test
    void testDelete() {
        User expected = new User();
        expected.setUsername("user");
        expected.setPassword(passwordEncoder.encode("user"));
        expected.setRole(roleService.findAll().getFirst());
        userRepository.save(expected);
        long before = userRepository.findById(expected.getUsername()).stream().count();
        userService.delete(expected.getUsername());
        long after = userRepository.findById(expected.getUsername()).stream().count();
        assertTrue(before > after);
    }
}
