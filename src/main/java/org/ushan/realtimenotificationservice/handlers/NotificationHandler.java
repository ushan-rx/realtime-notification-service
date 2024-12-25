package org.ushan.realtimenotificationservice.handlers;

import org.ushan.realtimenotificationservice.events.NotificationEvent;

/**
 * Interface for handling notification events.
 * Implementations define logic to handle specific event types.
 */
public interface NotificationHandler {

    /**
     * Determines if the handler can process the given event type.
     *
     * @param eventType the type of the event
     * @return {@code true} if the handler supports the event type, otherwise {@code false}
     */
    boolean canHandle(String eventType);

    /**
     * Processes the given notification event.
     *
     * @param event the notification event to handle
     */
    void handle(NotificationEvent event);
}
