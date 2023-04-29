package com.anas.chatty.security.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class Credentials {
    private String username;
    private String password;
}
