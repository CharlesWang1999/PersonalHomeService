package com.charles.user.domain.event;

import java.time.Instant;

/**
 * Event raised when a new user registers.
 */
public class UserRegisteredEvent implements DomainEvent {

    private final String userId;
    private final String email;
    private final Instant occurredOn;

    public UserRegisteredEvent(String userId, String email, Instant occurredOn) {
        this.userId = userId;
        this.email = email;
        this.occurredOn = occurredOn;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public Instant getOccurredOn() {
        return occurredOn;
    }
}
