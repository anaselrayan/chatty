package com.anas.chatty.websockets.sessions;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

public class RoomSessionHolder {
    private List<WebSocketSession> sessionList = new ArrayList<>();

    public RoomSessionHolder(WebSocketSession session) {
        this.sessionList.add(session);
    }

    public void addSession(WebSocketSession session) {
        try {
            sessionList.add(session);
        } catch (Exception e) {
            System.out.println("Cannot add session");
        }
    }

    public void removeSession(WebSocketSession session) {
        sessionList.remove(session);
    }

    public void broadcast(String message, WebSocketSession source) {
        for (WebSocketSession target : sessionList) {
            try {
                if (!source.getId().equals(target.getId()))
                    target.sendMessage(new TextMessage(message));
            } catch (Exception e) { // IOException && IllegalStateException
                sessionList.remove(target);
            }
        }
    }
}
