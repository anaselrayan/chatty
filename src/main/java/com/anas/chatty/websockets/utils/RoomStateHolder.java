package com.anas.chatty.websockets.utils;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class RoomStateHolder {
    private final Map<Long, RoomDetail> roomState = new HashMap<>();

    public void createBasicRoomDetail(Long roomId) {
        var rd = new RoomDetail(3L, 0L, false);
        this.roomState.put(roomId, rd);
    }
}
