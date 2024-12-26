package org.ushan.realtimenotificationservice.handlers;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.ushan.realtimenotificationservice.annotations.EventHandler;
import org.ushan.realtimenotificationservice.events.NotificationEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DynamicHandlerRegistry {
    private final Map<String, NotificationHandler> handlerMap = new HashMap<>();
    private final List<NotificationHandler> handlers;
    private final DefaultNotificationHandler defaultHandler;

    public DynamicHandlerRegistry(List<NotificationHandler> handlers, DefaultNotificationHandler defaultHandler) {
        this.handlers = handlers;
        this.defaultHandler = defaultHandler;
    }

    @PostConstruct
    private void initializeHandlers() {
        handlers.forEach(handler -> {
            EventHandler annotation = handler.getClass().getAnnotation(EventHandler.class);
            if (annotation != null) {
                handlerMap.put(annotation.value(), handler);
            }
        });
    }

    public void handleEvent(NotificationEvent event) {
        NotificationHandler handler = handlerMap.get(event.getType());
        if (handler != null) {
            handler.handle(event);
        } else {
            System.out.println("No handler found for event type: " + event.getType());
            defaultHandler.handle(event);
        }
    }
}
