package org.ushan.realtimenotificationservice.handlers;

import org.springframework.stereotype.Component;
import org.ushan.realtimenotificationservice.events.NotificationEvent;

import java.util.ArrayList;
import java.util.List;

@Component
public class DynamicHandlerRegistry {
    private final List<NotificationHandler> handlers = new ArrayList<>();
    public void registerHandler(NotificationHandler handler) {
        handlers.add(handler);
    }

    public void handleEvent(NotificationEvent event) {
        handlers.stream()
                .filter(handler -> handler.canHandle(event.getType()))
                .findFirst()
                .ifPresent(handler -> handler.handle(event));
    }
}
