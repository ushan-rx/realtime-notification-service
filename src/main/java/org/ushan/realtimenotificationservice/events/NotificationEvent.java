package org.ushan.realtimenotificationservice.events;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // to Include only non-null fields in JSON
public abstract class NotificationEvent {
    private String type;
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String sender;
    private String recipient;
    private Map<String, Object> metadata;

    protected NotificationEvent(String type, String message, String sender, String recipient) {
        this.type = type;
        this.message = message;
        this.sender = sender;
        this.recipient = recipient;
        this.timestamp = LocalDateTime.now();
    }}
