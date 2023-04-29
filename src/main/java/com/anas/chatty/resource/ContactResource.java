package com.anas.chatty.resource;

import com.anas.chatty.entity.Contact;
import com.anas.chatty.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contacts")
@RequiredArgsConstructor
public class ContactResource {
    private final ContactService contactService;

    @GetMapping("user/{userId}")
    public List<Contact> getContactsForUser(@PathVariable Long userId) {
        return contactService.getUserContactList(userId);
    }

    @GetMapping("/{contactId}")
    public Contact getContactById(@PathVariable Long contactId) {
        return contactService.getContactById(contactId);
    }

    @GetMapping("/find")
    public Contact getContactByPhone(@RequestParam String phone) {
        return contactService.getContactByPhone(phone);
    }

    @GetMapping("user/add")
    public Contact addContactForUser(@RequestParam String contactPhone,
                                     @RequestParam Long userId) {
        return contactService.addContactForUser(contactPhone, userId);
    }

    @DeleteMapping("user/delete")
    public Boolean removeContactForUser(@RequestParam Long userId,
                                        @RequestParam Long contactId) {
        return contactService.removeContactForUSer(userId, contactId);
    }

}
