package com.anas.chatty.websockets.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class RoomDetail {
    private Long max;
    private Long online;
    private boolean locked;
}
