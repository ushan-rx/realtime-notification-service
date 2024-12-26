package org.ushan.realtimenotificationservice.handlers.custom;

import org.springframework.stereotype.Component;
import org.ushan.realtimenotificationservice.annotations.EventHandler;
import org.ushan.realtimenotificationservice.events.NotificationEvent;
import org.ushan.realtimenotificationservice.events.custom.SystemAlertEvent;
import org.ushan.realtimenotificationservice.handlers.NotificationHandler;

@Component
@EventHandler("SYSTEM_ALERT")
public class SystemAlertHandler implements NotificationHandler {

    @Override
    public void handle(NotificationEvent event) {
        if (event instanceof SystemAlertEvent systemAlertEvent) {
            System.out.println("System Alert [" + systemAlertEvent.getAlertLevel() + "]: " + systemAlertEvent.getMessage());
        }
    }
}
