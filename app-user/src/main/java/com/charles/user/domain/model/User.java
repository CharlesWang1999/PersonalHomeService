package com.charles.user.domain.model;

import java.time.Instant;

/**
 * User aggregate root - domain model with no framework dependencies.
 * All fields are final to ensure immutability after construction.
 */
public class User {

    private final String id;
    private final String email;
    private final String passwordHash;
    private final boolean accountDisabled;
    private final Instant createdAt;
    private final Instant updatedAt;
    private final boolean deleted;

    public User(String id, String email, String passwordHash, boolean accountDisabled,
                Instant createdAt, Instant updatedAt, boolean deleted) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.accountDisabled = accountDisabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
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

    public boolean isDeleted() {
        return deleted;
    }

    public User withPasswordHash(String newPasswordHash) {
        return new User(id, email, newPasswordHash, accountDisabled, createdAt, Instant.now(), deleted);
    }

    public User withAccountDisabled(boolean disabled) {
        return new User(id, email, passwordHash, disabled, createdAt, Instant.now(), deleted);
    }

    public User withDeleted(boolean deleted) {
        return new User(id, email, passwordHash, accountDisabled, createdAt, Instant.now(), deleted);
    }
}
