package com.anas.chatty.dto;

import com.anas.chatty.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoomDTO {
    private String name;
    private Contact contact;
}
