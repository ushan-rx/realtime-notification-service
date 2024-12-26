package org.ushan.realtimenotificationservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.ushan.realtimenotificationservice.events.NotificationEvent;
import org.ushan.realtimenotificationservice.handlers.DynamicHandlerRegistry;

@Service
public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;
    private final DynamicHandlerRegistry handlerRegistry;

    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate, DynamicHandlerRegistry handlerRegistry) {
        this.messagingTemplate = messagingTemplate;
        this.handlerRegistry = handlerRegistry;
    }

    public void sendNotificationToUser(NotificationEvent event, String userId) {
        String userTopic = "/user/" + userId + "/queue/notifications";
        handlerRegistry.handleEvent(event);
        messagingTemplate.convertAndSendToUser(userId, userTopic, event);
    }

    public void sendBroadcastNotification(NotificationEvent event) {
        String broadcastTopic = "/topic/notifications";
        handlerRegistry.handleEvent(event);
        messagingTemplate.convertAndSend(broadcastTopic, event);
    }
}
