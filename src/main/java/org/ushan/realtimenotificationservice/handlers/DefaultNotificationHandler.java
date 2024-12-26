package org.ushan.realtimenotificationservice.handlers;

import org.springframework.stereotype.Component;
import org.ushan.realtimenotificationservice.events.NotificationEvent;

@Component
public class DefaultNotificationHandler implements NotificationHandler{
    @Override
    public void handle(NotificationEvent event) {
        System.out.println("Handling default event: " + event.getMessage());
    }
}
