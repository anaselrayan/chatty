package com.anas.chatty.security.model;

import com.anas.chatty.entity.AppUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public class AuthenticationResponse {
    private final AppUser user;
    private final Map<String, String> tokens;
}
