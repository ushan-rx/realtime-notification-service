package org.ushan.realtimenotificationservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ushan.realtimenotificationservice.events.custom.FriendRequestEvent;
import org.ushan.realtimenotificationservice.events.custom.SystemAlertEvent;

@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Dynamically register subclasses
        mapper.registerSubtypes(
                new NamedType(SystemAlertEvent.class, "SYSTEM_ALERT"),
                new NamedType(FriendRequestEvent.class, "FRIEND_REQUEST")
                // Add other notification subclasses here as needed
        );
        return mapper;
    }
}
