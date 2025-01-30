package com.cims.computerinventorymanagmentsystem.controller;

import com.cims.computerinventorymanagmentsystem.model.Role;
import com.cims.computerinventorymanagmentsystem.model.User;
import com.cims.computerinventorymanagmentsystem.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUserName(username);
        return ResponseEntity.ok(user);
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @PostMapping
    public ResponseEntity<User> addUser(
            @RequestParam Role role,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String firstName,
            @RequestParam String lastName) {

        User newUser = userService.addUser(role, username, password, firstName, lastName);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUserByUsername(@PathVariable String username) {
        userService.deleteUserByUserName(username);
        return ResponseEntity.ok("User deleted successfully");
    }
}

