package org.ushan.realtimenotificationservice.events;

import lombok.*;


@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class SystemAlertEvent extends NotificationEvent{

    private AlertLevel alertLevel;

    public SystemAlertEvent(String message, String recipient, AlertLevel alertLevel) {
        super("SYSTEM_ALERT", message, "SYSTEM", recipient);
        this.alertLevel = alertLevel;
    }

    /**
     * Enum representing the levels of alerts in the system.
     */
    public enum AlertLevel{
        DEBUG,       // Detailed information for debugging purposes
        TRACE,       // Fine-grained details capturing the flow of the program
        INFO,        // General informational messages
        NOTICE,      // Significant events requiring attention
        WARN,        // Potential issues to address
        ERROR,       // Errors that impact functionality but not system operation
        ALERT,       // High-priority issues needing immediate attention
        CRITICAL,    // Severe issues risking system instability or failure
        FATAL,       // Critical errors causing system failure
        SUCCESS,     // Successful completion of an operation
        RECOVERY,    // Recovery from an error or failure
        PENDING,     // Ongoing process awaiting completion
        UNKNOWN      // Undetermined alert level
    }
}
