package com.anas.chatty.security.controller;

import com.anas.chatty.security.model.AuthenticationResponse;
import com.anas.chatty.security.model.LoginRequest;
import com.anas.chatty.security.model.RegisterRequest;
import com.anas.chatty.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        var response = authService.registerUser(request);
        if (response != null)
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
        var response = authService.loginUser(request);
        if (response != null)
            return new ResponseEntity<>(response, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
