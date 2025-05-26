package com.backend.service;

import com.backend.entity.User;
import com.backend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return find(username);
    }

    public User find(String username) {
        return userRepository.findById(username).orElseThrow();
    }

    public User update(String username, User newUser) {
        return userRepository.findById(username).map(user -> {
            newUser.setUsername(user.getUsername());
            return userRepository.save(newUser);
        }).orElseThrow();
    }

    public void delete(String username) {
        userRepository.deleteById(username);
    }
}
