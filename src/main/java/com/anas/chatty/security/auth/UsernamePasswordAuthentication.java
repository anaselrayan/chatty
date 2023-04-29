package com.anas.chatty.security.auth;

import com.anas.chatty.security.model.SecurityUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UsernamePasswordAuthentication implements Authentication {
    private final SecurityUserDetails userDetails;
    private final Credentials credentials;
    private boolean authenticated;

    public UsernamePasswordAuthentication(Credentials credentials) {
        this.credentials = credentials;
        authenticated = false;
        userDetails = null;
    }

    public UsernamePasswordAuthentication(SecurityUserDetails userDetails) {
        this.userDetails = userDetails;
        authenticated = true;
        credentials = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails != null ? userDetails.getAuthorities() : null;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userDetails != null ? userDetails.getUser(): null;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return UsernamePasswordAuthentication.class.getName();
    }
}
