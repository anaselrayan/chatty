package com.anas.chatty.websockets.handshake;

import com.anas.chatty.utils.HttpUtil;
import com.anas.chatty.websockets.utils.RoomDetail;
import com.anas.chatty.websockets.utils.RoomStateHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class RoomHandshakeInterceptor implements HandshakeInterceptor {
    private final RoomStateHolder stateHolder;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {

        String uri = String.valueOf(request.getURI());
        String roomId = HttpUtil.extractParams(uri).get("room_id");
        RoomDetail rd = stateHolder.getRoomState().get(Long.valueOf(roomId));
        if (rd != null && !rd.isLocked() && rd.getMax() > rd.getOnline()) {
            rd.setOnline(rd.getOnline() + 1);
            System.out.println("Room(" + roomId + "): " + rd.getOnline());
        } else  {
            // THE BETTER WAY TO DO THIS BY RETURNING 'false'
            // BUT I USE 'SOCKJS' IN THE FRONTEND AND I DIDN'T FIND WAY TO HANDLE
            // HANDSHAKE ERROR!
            attributes.put("block", true);
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception exception) {
    }
}
