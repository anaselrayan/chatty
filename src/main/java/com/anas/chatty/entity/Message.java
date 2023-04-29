package com.anas.chatty.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @OneToOne
    private Contact source;
    @OneToOne
    private Contact dest;
    private LocalDateTime sentAt;

    public Message(String content, Contact source, Contact dest) {
        this.content = content;
        this.source = source;
        this.dest = dest;
        this.sentAt = LocalDateTime.now();
    }
}