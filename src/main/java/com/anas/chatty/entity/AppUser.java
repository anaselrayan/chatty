package com.anas.chatty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "users")
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "contact_id")
    private Contact contactInfo;

    @ManyToMany
    @JoinTable(
            name = "users_contacts", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private List<Contact> contactList;

    @JsonIgnore
    private String password;

    private LocalDateTime joinedAt;

    public AppUser(Contact contactInfo, String password) {
        this.contactInfo = contactInfo;
        this.password = password;
        this.joinedAt = LocalDateTime.now();
    }

    public void addContact(Contact contact) {
        this.contactList.add(contact);
    }
}
