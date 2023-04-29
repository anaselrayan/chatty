package com.anas.chatty.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class RegisterRequest {
    private String fullName;
    private String phone;
    private String password;
}
