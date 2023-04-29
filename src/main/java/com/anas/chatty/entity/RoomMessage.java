package com.anas.chatty.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class RoomMessage {
    private String content;
    private Contact source;
    private LocalDateTime sentAt;
    private MessageType type;
}
