package com.backend.controller;

import com.backend.entity.User;
import com.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/users")
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    @PostMapping(value = "/users")
    public User saveUser(@RequestBody User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userService.save(newUser);
    }

    @GetMapping(value = "/users/{username}")
    public User findUser(@PathVariable String username) {
        return userService.find(username);
    }

    @PutMapping(value = "/users/{username}")
    public User updateUser(@PathVariable String username, @RequestBody User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userService.update(username, newUser);
    }

    @DeleteMapping(value = "/users/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable String username) {
        userService.delete(username);
        return ResponseEntity.ok().build();
    }
}
