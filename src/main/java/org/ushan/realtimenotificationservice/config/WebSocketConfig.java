package org.ushan.realtimenotificationservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Configures WebSocket communication using STOMP protocol in a Spring application.
 *
 * Key Features:
 * - Registers the STOMP endpoint "/ws" for WebSocket connections with SockJS fallback.
 * - Enables a simple in-memory message broker for messaging with destinations "/topic" and "/queue".
 * - Sets application-specific prefix "/app" for routing messages to controllers.
 * - Supports private messaging with the prefix "/user".
 *
 * Use this configuration to handle real-time messaging and enable WebSocket communication
 * between the server and clients.
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/queue");
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }
}