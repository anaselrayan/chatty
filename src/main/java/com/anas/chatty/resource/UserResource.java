package com.anas.chatty.resource;

import com.anas.chatty.entity.AppUser;
import com.anas.chatty.security.model.SecurityUserDetails;
import com.anas.chatty.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserResource {
    private final AppUserService userService;

    @GetMapping("{phone}")
    public AppUser getUserByPhone(@PathVariable String phone) {
        var ud = (SecurityUserDetails) userService.loadUserByUsername(phone);
        return ud.getUser();
    }
}
