package com.charles.user.application.dto;

/**
 * DTO for user registration response.
 */
public class RegisterResponse {

    private final String userId;
    private final String email;

    public RegisterResponse(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }
}
