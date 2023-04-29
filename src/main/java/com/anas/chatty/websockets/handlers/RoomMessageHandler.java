package com.anas.chatty.websockets.handlers;

import com.anas.chatty.utils.HttpUtil;
import com.anas.chatty.websockets.sessions.RoomSessionHolder;
import com.anas.chatty.websockets.utils.RoomDetail;
import com.anas.chatty.websockets.utils.RoomStateHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class RoomMessageHandler implements WebSocketHandler {
//    Map<"group_id", SessionManger>
    private final Map<String, RoomSessionHolder> groupHandlers = new ConcurrentHashMap<>();
    private final RoomStateHolder stateHolder;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Object sessionBlocked = session.getAttributes().get("block");
        if (sessionBlocked != null)
            session.close();
        else {
            String uri = String.valueOf(session.getUri());
            String roomId = HttpUtil.extractParams(uri).get("room_id");
            session.getAttributes().put("room_id", roomId);

            if (groupHandlers.containsKey(roomId)) {
                groupHandlers.get(roomId).addSession(session);
            } else {
                groupHandlers.put(roomId, new RoomSessionHolder(session));
            }
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = message.getPayload().toString();
        String groupId = session.getAttributes().get("room_id").toString();
        groupHandlers.get(groupId).broadcast(payload, session);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        Object sessionBlocked = session.getAttributes().get("block");
        if (sessionBlocked == null) {
            String roomId = session.getAttributes().get("room_id").toString();
            groupHandlers.get(roomId).removeSession(session);
            RoomDetail rd = stateHolder.getRoomState().get(Long.valueOf(roomId));
            rd.setOnline(rd.getOnline() - 1);
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
