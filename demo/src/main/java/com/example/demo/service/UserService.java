package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    List<User> usersList();
    User getUserById(int id);
    void updateUser(User user);
    void removeUser(int id);
    User findByUsername(String name);
}
