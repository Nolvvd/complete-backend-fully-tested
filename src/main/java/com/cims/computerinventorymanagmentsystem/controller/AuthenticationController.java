package com.cims.computerinventorymanagmentsystem.controller;


import com.cims.computerinventorymanagmentsystem.model.AuthenticationResponse;
import com.cims.computerinventorymanagmentsystem.model.User;
import com.cims.computerinventorymanagmentsystem.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login (
            @RequestBody User request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
