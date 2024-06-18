package org.example.practice.controller;


import org.example.practice.entity.User;
import org.example.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }
    @GetMapping("/email")
    public User getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/add")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    @PutMapping("/update")
    public User updateUser(@RequestParam int id, @RequestParam String email, @RequestParam String name) {
        return userService.updateUser(id, email, name);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
