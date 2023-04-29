package com.anas.chatty.config;

import com.anas.chatty.entity.AppUser;
import com.anas.chatty.entity.Contact;
import com.anas.chatty.service.AppUserService;
import com.anas.chatty.service.ContactService;
import com.anas.chatty.service.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;

//@Component
@RequiredArgsConstructor
public class StartUp implements CommandLineRunner {
    private final AppUserService userService;
    private final ContactService contactService;
    private final MessagesService messagesService;

    @Override
    public void run(String... args) throws Exception {

    }
}
