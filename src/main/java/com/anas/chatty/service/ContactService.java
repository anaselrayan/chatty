package com.anas.chatty.service;

import com.anas.chatty.entity.AppUser;
import com.anas.chatty.entity.Contact;
import com.anas.chatty.repo.ContactRepository;
import com.anas.chatty.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private final JdbcTemplate jdbcTemplate;

    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }

    public void addContacts(List<Contact> contactList) {
        contactRepository.saveAll(contactList);
    }

    public List<Contact> getUserContactList(Long userId) {
        return contactRepository.findAllByUserId(userId);
    }

    public Contact getContactById(Long contactId) {
        return contactRepository.findById(contactId).orElse(null);
    }

    public Contact getContactByPhone(String phone) {
        return contactRepository.findByPhone(phone);
    }

    public Contact addContactForUser(String contactPhone, Long userId) {
        var contact = contactRepository.findByPhone(contactPhone);
        if (contact != null) {
            var user = userRepository.findById(userId);
            if (user.isPresent() && !alreadyHasContact(user.get(), contact.getId())) {
                return addContactForUser(userId, contact.getId()) ? contact : null;
            }
        }
        return null;
    }

    private boolean addContactForUser(long userId, long contactId) {
        String query =
                "INSERT INTO users_contacts (user_id, contact_id) VALUES (" + userId + ", " + contactId + ")";
        try {
           jdbcTemplate.execute(query);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean removeContactForUSer(long userId, long contactId) {
        String query
                = "DELETE FROM users_contacts WHERE user_id = " + userId  + " AND contact_id = " + contactId;
        try {
            jdbcTemplate.execute(query);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    private boolean alreadyHasContact(AppUser user, long contactId) {
        for (Contact c : user.getContactList()) {
            if (c.getId() == contactId)
                return true;
        }
        return false;
    }
}
