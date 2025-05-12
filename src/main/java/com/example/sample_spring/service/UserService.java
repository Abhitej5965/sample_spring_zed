package com.example.sample_spring.service;

import com.example.sample_spring.model.User;
import java.util.List;

public interface UserService {
    User createUser(User user);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
    User getUserById(Long id);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}