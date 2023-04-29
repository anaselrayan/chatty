package com.anas.chatty.websockets.handlers;

import com.anas.chatty.entity.Message;
import com.anas.chatty.repo.MessagesRepository;
import com.anas.chatty.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AppMessageHandler implements WebSocketHandler {
    private final MessagesRepository messagesRepository;
    // <String: userID, Session>
    private final ConcurrentHashMap<String, WebSocketSession> sessions;

    @Autowired
    public AppMessageHandler(MessagesRepository repository) {
        this.messagesRepository = repository;
        this.sessions = new ConcurrentHashMap<>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String uri = String.valueOf(session.getUri());
        String user_id = HttpUtil.extractParams(uri).get("user_id");
        sessions.put(user_id, session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        String payload = message.getPayload().toString();
        JSONObject obj = new JSONObject(payload);
        var dest = obj.getJSONObject("dest");
        var src = obj.getJSONObject("source");
        long destId = dest.getLong("id");
        long srcId = src.getLong("id");
        var target = sessions.get(String.valueOf(destId));

        try {
            if (target != null && srcId != destId)
                target.sendMessage(new TextMessage(JSONObject.valueToString(obj)));
        } catch (Exception e) { // for IOException & IllegalStateException
            sessions.remove(String.valueOf(destId));
        }

        /* STORING THE MESSAGE IN THE DATABASE */
        new Thread(()-> {
            var mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            Message msg = null;
            try {
                msg = mapper.readValue(payload, Message.class);
                msg.setSentAt(msg.getSentAt().plusHours(2));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                if (msg != null)
                    messagesRepository.save(msg);
            }
        }).start();
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//        sessions.remove(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
