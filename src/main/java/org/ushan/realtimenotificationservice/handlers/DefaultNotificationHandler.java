package org.ushan.realtimenotificationservice.handlers;

import org.ushan.realtimenotificationservice.events.NotificationEvent;

public class DefaultNotificationHandler implements NotificationHandler{
    @Override
    public boolean canHandle(String eventType) {
        return "DEFAULT_EVENT".equals(eventType);
    }

    @Override
    public void handle(NotificationEvent event) {
        System.out.println("Handling default event: " + event.getMessage());
    }
}
