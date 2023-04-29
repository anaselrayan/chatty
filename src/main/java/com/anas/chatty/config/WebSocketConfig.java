package com.anas.chatty.config;

import com.anas.chatty.repo.MessagesRepository;
import com.anas.chatty.websockets.handlers.AppMessageHandler;
import com.anas.chatty.websockets.handlers.RoomMessageHandler;
import com.anas.chatty.websockets.handshake.RoomHandshakeInterceptor;
import com.anas.chatty.websockets.utils.RoomStateHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    private final MessagesRepository repository;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(messageHandler(), "/ws/messages")
                .setAllowedOrigins("http://localhost:4200")
                .withSockJS();

        registry.addHandler(groupHandler(), "/ws/rooms")
                .addInterceptors(interceptor())
                .setAllowedOrigins("http://localhost:4200")
                .withSockJS();
    }

    @Bean
    public WebSocketHandler messageHandler() {
        return new AppMessageHandler(repository);
    }

    @Bean
    public WebSocketHandler groupHandler() { return new RoomMessageHandler(roomStateHolder()); }

    @Bean
    public RoomStateHolder roomStateHolder() {
        return new RoomStateHolder();
    }

    @Bean
    public HandshakeInterceptor interceptor() {
        return new RoomHandshakeInterceptor(roomStateHolder());
    }

}
