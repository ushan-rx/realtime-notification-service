package org.ushan.realtimenotificationservice.events;

import lombok.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BroadcastNotification extends NotificationEvent{
    public BroadcastNotification(String type, String message, String sender) {
        super(type, message, sender, null);
    }

    public abstract void handle(SimpMessagingTemplate messagingTemplate, String broadcastTopic);
}
