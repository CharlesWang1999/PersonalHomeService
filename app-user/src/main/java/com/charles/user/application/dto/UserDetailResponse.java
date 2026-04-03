package com.charles.user.application.dto;

import java.time.Instant;

/**
 * DTO for user detail response.
 */
public class UserDetailResponse {

    private final String id;
    private final String email;
    private final boolean accountDisabled;
    private final Instant createdAt;
    private final Instant updatedAt;

    public UserDetailResponse(String id, String email, boolean accountDisabled,
                              Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.email = email;
        this.accountDisabled = accountDisabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAccountDisabled() {
        return accountDisabled;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
