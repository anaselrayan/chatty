package com.anas.chatty.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rooms")
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String background;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    public Room(String name, Contact contact) {
        this.name = name;
        this.contact = contact;
    }
}
