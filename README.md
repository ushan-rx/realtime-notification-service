# Notification Service

This repository contains a **Notification Service** implemented using **Spring Boot** and **WebSockets**. The service provides an extensible framework for handling and delivering notifications, supporting both user-specific notifications and broadcast notifications.

> **Note**: This project is intended for educational purposes and is not production-ready. For optimal performance and scalability in real-world applications, tools like RabbitMQ could be used instead of REST APIs for communication.

---

## Features

### 1. Extensible Notification Model

- **Base Class**: `NotificationEvent`
- Easily supports custom notifications through subclassing.

### 2. Two Types of Notifications

- **User-Specific Notifications**: Delivered to individual users.
- **Broadcast Notifications**: Sent to all subscribers of a specific topic.

### 3. WebSocket Integration

- Real-time messaging using `SimpMessagingTemplate`.

### 4. REST API Support

- Easily trigger notifications via REST endpoints.

---

## Project Structure

### Event Classes

- **`NotificationEvent`**: Abstract base class for all notifications.
- **`UserSpecificNotification`**: Base class for user-specific events.
- **`BroadcastNotification`**: Base class for broadcast events.
- **Custom Implementations**:
    - `FriendRequestEvent`
    - `SystemAlertEvent`

### Service Classes

- **`NotificationService`**: Manages the delivery of user-specific and broadcast notifications.

### Controller

- **`NotificationController`**: Provides REST endpoints for triggering notifications.

---

## Prerequisites

1. **Java**: 17 or higher
2. **Maven**: 3.8+
3. **Spring Boot**: 2.7+

---

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/yourusername/notification-service.git
cd notification-service
```

### Build and Run

1. **Build the application**:

   ```bash
   mvn clean install
   ```

2. **Run the application**:

   ```bash
   mvn spring-boot:run
   ```

### Accessing the Service

- **WebSocket Endpoint**: `/ws`
- **REST Endpoints**:
    - Send user-specific notification: `POST /api/notifications/custom/{userId}`
    - Send broadcast notification: `POST /api/notifications/broadcast`

---

## Example Usage

### User-Specific Notification

**Request**:

```bash
POST /api/notifications/custom/{userId}
```

**Payload**:

```json
{
  "type": "FRIEND_REQUEST",
  "message": "You have a new friend request!",
  "sender": "John",
  "recipient": "Jane",
  "friendRequestType": "REQUESTED"
}
```

### Broadcast Notification

**Request**:

```bash
POST /api/notifications/broadcast
```

**Payload**:

```json
{
  "type": "SYSTEM_ALERT",
  "message": "System maintenance scheduled at midnight.",
  "alertLevel": "INFO"
}
```

---

## Extending the Service

### Creating Custom Notification Events

Developers can create their own custom notification events by following these steps:

#### 1. Extend the Appropriate Base Class

- For user-specific notifications, extend `UserSpecificNotification`.
- For broadcast notifications, extend `BroadcastNotification`.

#### 2. Implement the `handle` Method

Define how the event should be processed and delivered.

#### 3. Register the Subclass with Jackson

Update `JacksonConfig` to include the new subclass:

```java
mapper.registerSubtypes(new NamedType(MyCustomEvent.class, "MY_CUSTOM_EVENT"));
```

### Example: Custom Event

```java
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MyCustomEvent extends UserSpecificNotification {

    private String customField;

    public MyCustomEvent(String message, String sender, String recipient, String userId, String customField) {
        super("MY_CUSTOM_EVENT", message, sender, recipient, userId);
        this.customField = customField;
    }

    @Override
    public void handle(SimpMessagingTemplate messagingTemplate, String userTopic, String userId) {
        messagingTemplate.convertAndSendToUser(userId, userTopic + "/custom", this);
    }
}
```

After defining the new event class, you can use it in your REST endpoints just like the existing events.

---

## Possible Improvements ⌛

- Extend WebSocket integration to support authentication.

- Add database support for notification persistence. (optional)

- Replace REST API for event triggering with RabbitMQ or Kafka for better scalability.

---

Thank you for exploring the Notification Service! Contributions are always welcome.

