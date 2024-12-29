package org.ushan.realtimenotificationservice.events.custom;

import lombok.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.ushan.realtimenotificationservice.events.NotificationEvent;
import org.ushan.realtimenotificationservice.events.UserSpecificNotification;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequestEvent extends UserSpecificNotification {

    private FriendRequestType friendRequestType;

    public FriendRequestEvent(String sender, String recipient, String message, FriendRequestType friendRequestType, String userId) {
        super("FRIEND_REQUEST", message, sender, recipient, userId);
        this.friendRequestType = friendRequestType;
    }

    @Override
    public void handle(SimpMessagingTemplate messagingTemplate, String userTopic, String userId) {
//        "/user/user_id/queue/friend_request"
        messagingTemplate.convertAndSendToUser(userId, userTopic+"/friend_request", this);
    }

    /**
     * Enum representing the type(status) of the friend request.
     */
    public enum FriendRequestType{
        REQUESTED,  // to notify when the request is sent first
        ACCEPTED  // to notify back when the request is accepted
    }
}
