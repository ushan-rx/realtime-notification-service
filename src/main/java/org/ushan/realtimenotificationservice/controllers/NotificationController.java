package org.ushan.realtimenotificationservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.ushan.realtimenotificationservice.events.NotificationEvent;
import org.ushan.realtimenotificationservice.services.NotificationService;

@Controller
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/custom/{userId}")
    public void sendCustomNotification(@PathVariable String userId, @RequestBody NotificationEvent event) {
        notificationService.sendNotificationToUser(event, userId);
    }

    @PostMapping("/broadcast")
    public void sendBroadcastNotification(@Validated @RequestBody NotificationEvent event) {
        notificationService.sendBroadcastNotification(event);
    }
}
