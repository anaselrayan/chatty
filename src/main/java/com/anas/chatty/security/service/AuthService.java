package com.anas.chatty.security.service;

import com.anas.chatty.entity.AppUser;
import com.anas.chatty.entity.Contact;
import com.anas.chatty.security.model.*;
import com.anas.chatty.service.AppUserService;
import com.anas.chatty.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final ValidationService validationService;
    private final AppUserService userService;
    private final ContactService contactService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse registerUser(RegisterRequest request) {
        if (validationService.validRequest(request)) {
            var userDetails = (SecurityUserDetails) userService.loadUserByUsername(request.getPhone());
            if (userDetails == null) {
                Contact c = new Contact(request.getFullName(), request.getPhone());
                c.setImageUrl(getRandomImageUrl());
                contactService.addContact(c);
                var user = userService.addUser(new AppUser(c, request.getPassword()));
                return new AuthenticationResponse(user, null);
            }
        }
        return null;
    }

    public AuthenticationResponse loginUser(LoginRequest request) {
        if (validationService.validRequest(request)) {
            var userDetails = (SecurityUserDetails) userService.loadUserByUsername(request.getPhone());
            if (userDetails != null && matchPassword(request.getPassword(), userDetails.getPassword()))
                return new AuthenticationResponse(userDetails.getUser(), null);
        }
        return null;
    }

    private boolean matchPassword(String userInput, String userPassword) {
        return passwordEncoder.matches(userInput, userPassword);
    }

    private String getRandomImageUrl() {
        int r1 = new Random().nextInt(300) + 1;
        int r2 = new Random().nextInt(300) + 1;
        int r3 = new Random().nextInt(300) + 1;
        int r4 = new Random().nextInt(300) + 1;
        return "https://robohash.org/" + r1 + "." + r2 + "." + r3 + "." + r4 + ".png";
    }
}
