package com.anas.chatty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "contacts")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    private String imageUrl;

    @ManyToMany(mappedBy = "contactList")
    @JsonIgnore
    private List<AppUser> users;

    @OneToMany
    @JsonIgnore
    private List<Room> rooms;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
