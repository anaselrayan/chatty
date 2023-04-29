package com.anas.chatty.service;

import com.anas.chatty.entity.AppUser;
import com.anas.chatty.repo.UserRepository;
import com.anas.chatty.security.model.SecurityUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUser addUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setContactList(List.of(user.getContactInfo()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        var user = userRepository.findByContactInfoPhone(phone);
        return user.map(SecurityUserDetails::new).orElse(null);
    }

}
