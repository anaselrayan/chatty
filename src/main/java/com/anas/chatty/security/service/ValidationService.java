package com.anas.chatty.security.service;

import com.anas.chatty.security.model.LoginRequest;
import com.anas.chatty.security.model.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public boolean validPhoneNumber(String phone) {
        return phone != null && !phone.isEmpty();
    }

    public boolean validPassword(String password) {
        return password != null && !password.isEmpty();
    }

    public boolean validUsername(String username) {
        return username != null
                && !username.isEmpty();
    }

    public boolean validRequest(RegisterRequest request) {
        return  validUsername(request.getFullName())
                && validPhoneNumber(request.getPhone())
                && validPassword(request.getPassword());
    }

    public boolean validRequest(LoginRequest request) {
        return  validPhoneNumber(request.getPhone())
                && validPassword(request.getPassword());
    }
}
