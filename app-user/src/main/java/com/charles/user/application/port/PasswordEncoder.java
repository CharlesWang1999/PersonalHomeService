package com.charles.user.application.port;

/**
 * Port interface for password encoding - defined in application layer,
 * implemented in infrastructure layer.
 */
public interface PasswordEncoder {

    String encode(String rawPassword);

    boolean matches(String rawPassword, String encodedPassword);
}
