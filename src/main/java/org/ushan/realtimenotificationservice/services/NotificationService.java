package org.ushan.realtimenotificationservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.ushan.realtimenotificationservice.events.BroadcastNotification;
import org.ushan.realtimenotificationservice.events.UserSpecificNotification;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public <T extends UserSpecificNotification> void sendNotificationToUser(T event, String userId) {
        try {
            String userTopic = "/user/" + userId + "/queue";
            logger.info("Sending user-specific notification to userId: {} with event: {}", userId, event);
            event.handle(messagingTemplate, userTopic, userId);
        } catch (Exception e) {
            logger.error("Failed to send user-specific notification", e);
            throw e;
        }
    }

    public  <T extends BroadcastNotification> void sendBroadcastNotification(T event) {
        try {
            String broadcastTopic = "/topic";
            logger.info("Sending broadcast notification with event: {}", event);
            event.handle(messagingTemplate, broadcastTopic);
        } catch (Exception e) {
            logger.error("Failed to send broadcast notification", e);
            throw e;
        }
    }
}
