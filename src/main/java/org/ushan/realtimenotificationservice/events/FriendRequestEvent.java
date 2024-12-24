package org.ushan.realtimenotificationservice.events;

import lombok.*;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequestEvent extends NotificationEvent{

    private FriendRequestType friendRequestType;
    public FriendRequestEvent(String sender, String recipient, String message, FriendRequestType friendRequestType) {
        super("FRIEND_REQUEST", message, sender, recipient);
        this.setFriendRequestType(friendRequestType);
    }
    /**
     * Enum representing the type(status) of the friend request.
     */
    public enum FriendRequestType{
        REQUESTED,  // to notify when the request is sent first
        ACCEPTED  // to notify back when the request is accepted
    }
}
