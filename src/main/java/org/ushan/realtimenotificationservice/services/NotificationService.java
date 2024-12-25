package org.ushan.realtimenotificationservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.ushan.realtimenotificationservice.events.NotificationEvent;
import org.ushan.realtimenotificationservice.handlers.DynamicHandlerRegistry;

public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;
    private final DynamicHandlerRegistry handlerRegistry;

    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate, DynamicHandlerRegistry handlerRegistry) {
        this.messagingTemplate = messagingTemplate;
        this.handlerRegistry = handlerRegistry;
    }

    public void sendNotification(NotificationEvent event, String topic) {
        handlerRegistry.handleEvent(event);
        messagingTemplate.convertAndSend(topic, event);
    }
}
