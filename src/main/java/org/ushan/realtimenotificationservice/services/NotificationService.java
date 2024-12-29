package org.ushan.realtimenotificationservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.ushan.realtimenotificationservice.events.BroadcastNotification;
import org.ushan.realtimenotificationservice.events.NotificationEvent;
import org.ushan.realtimenotificationservice.events.UserSpecificNotification;

@Service
public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public <T extends UserSpecificNotification> void sendNotificationToUser(T event, String userId) {
        String userTopic = "/user/" + userId + "/queue";
        event.handle(messagingTemplate, userTopic, userId);
    }

    public  <T extends BroadcastNotification> void sendBroadcastNotification(T event) {
        String broadcastTopic = "/topic/notifications";
        event.handle(messagingTemplate, broadcastTopic);
    }
}
