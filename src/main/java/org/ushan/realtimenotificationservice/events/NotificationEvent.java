package org.ushan.realtimenotificationservice.events;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // to Include only non-null fields in JSON
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,        // Use a name to identify the type
        include = JsonTypeInfo.As.PROPERTY, // Include type info as a property
        property = "type",                 // Field name in the JSON
        visible = true                     // Allow `type` to be visible for subclasses
)
public abstract class NotificationEvent {
    private String type;
    private String message;
    private long timestamp = System.currentTimeMillis(); // Milliseconds since epoch
    private String sender;
    private String recipient;
    private Map<String, Object> metadata;

    protected NotificationEvent(String type, String message, String sender, String recipient) {
        this.type = type;
        this.message = message;
        this.sender = sender;
        this.recipient = recipient;
        this.timestamp = System.currentTimeMillis();
    }

}
