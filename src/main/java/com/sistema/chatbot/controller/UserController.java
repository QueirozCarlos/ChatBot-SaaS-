package com.sistema.chatbot.controller;

import com.sistema.chatbot.model.User;
import com.sistema.chatbot.model.UserEntity;
import com.sistema.chatbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Optional<UserEntity> getUserById(@PathVariable int id) {
        return userService.findUserById(Long.valueOf(id));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
