package org.ushan.realtimenotificationservice.events;

import lombok.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract  class UserSpecificNotification extends NotificationEvent {

    private String userId; // Specific to user-based notifications

    public UserSpecificNotification(String type, String message, String sender, String recipient, String userId) {
        super(type, message, sender, recipient);
        this.userId = userId;
    }

    public abstract void handle(SimpMessagingTemplate messagingTemplate, String userTopic, String userId);
}
