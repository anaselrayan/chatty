package com.anas.chatty.repo;

import com.anas.chatty.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    @Query("""
        SELECT u from AppUser u WHERE u.contactInfo.phone = :phone
    """)
    Optional<AppUser> findByContactInfoPhone(String phone);


}
