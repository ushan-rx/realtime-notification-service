package org.ushan.realtimenotificationservice.handlers;

import org.ushan.realtimenotificationservice.events.NotificationEvent;

/**
 * Interface for handling notification events.
 * Implementations define logic to handle specific event types.
 */
public interface NotificationHandler {

    /**
     * Processes the given notification event.
     *
     * @param event the notification event to handle
     */
    void handle(NotificationEvent event);
}
