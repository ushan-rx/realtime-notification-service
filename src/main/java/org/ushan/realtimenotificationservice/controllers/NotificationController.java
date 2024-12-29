package org.ushan.realtimenotificationservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.ushan.realtimenotificationservice.events.BroadcastNotification;
import org.ushan.realtimenotificationservice.events.UserSpecificNotification;
import org.ushan.realtimenotificationservice.services.NotificationService;

@RestController
@RequestMapping("api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

     @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/user/{userId}")
    public <T extends UserSpecificNotification> ResponseEntity<String> sendCustomNotification(@PathVariable String userId, @Validated @RequestBody T event) {
        notificationService.sendNotificationToUser(event, userId);
        return ResponseEntity.ok("Notification sent to user: " + userId);
    }

    @PostMapping("/broadcast")
    public <T extends BroadcastNotification> ResponseEntity<String> sendBroadcastNotification(@Validated @RequestBody T event) {
        System.out.println(event);
        notificationService.sendBroadcastNotification(event);
        return ResponseEntity.ok("Broadcast notification sent.");
    }
}
