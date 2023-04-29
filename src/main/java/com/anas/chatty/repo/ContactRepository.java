package com.anas.chatty.repo;

import com.anas.chatty.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@EnableTransactionManagement
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByPhone(String phone);

    @Query(value = """
        SELECT DISTINCT c from Contact c JOIN c.users u WHERE u.id = :userId
    """)
    List<Contact> findAllByUserId(Long userId);

    @Transactional()
    @Query(value = """
        INSERT INTO users_contacts (user_id, contact_id) VALUES (?, ?)
    """, nativeQuery = true)
    void addContactForUser(long userId, long contactId);
}
