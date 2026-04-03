package com.charles.user.infrastructure.persistence;

import jakarta.persistence.*;
import java.time.Instant;

/**
 * JPA entity for User - belongs to infrastructure layer.
 * Contains all persistence annotations.
 */
@Entity
@Table(name = "users")
public class UserJpaEntity {

    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "account_disabled", nullable = false)
    private boolean accountDisabled;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(nullable = false)
    private boolean deleted;

    protected UserJpaEntity() {
    }

    public UserJpaEntity(String id, String email, String passwordHash, boolean accountDisabled,
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

    public com.charles.user.domain.model.User toDomain() {
        return new com.charles.user.domain.model.User(
                id, email, passwordHash, accountDisabled, createdAt, updatedAt, deleted
        );
    }
}
