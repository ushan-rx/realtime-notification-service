package org.ushan.realtimenotificationservice.handlers.custom;

import org.springframework.stereotype.Component;
import org.ushan.realtimenotificationservice.annotations.EventHandler;
import org.ushan.realtimenotificationservice.events.NotificationEvent;
import org.ushan.realtimenotificationservice.events.custom.FriendRequestEvent;
import org.ushan.realtimenotificationservice.handlers.NotificationHandler;

import java.util.Date;
@Component
@EventHandler("FRIEND_REQUEST")
public class FriendRequestHandler implements NotificationHandler {
    @Override
    public void handle(NotificationEvent event) {
        if (event instanceof FriendRequestEvent friendRequestEvent) {
            System.out.println(friendRequestEvent.getTimestamp()
                    + " :Friend request from "
                    + friendRequestEvent.getSender()
                    + " to " + friendRequestEvent.getRecipient()
                    + " - type " + friendRequestEvent.getFriendRequestType());
        }
    }
}
