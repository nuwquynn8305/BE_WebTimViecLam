package com.example.WebTimViecLam.Service;

import com.example.WebTimViecLam.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User register(User user);
    User login(String email, String password);
    Optional<User> findById(String id);
    List<User> getAllUsers();
}
