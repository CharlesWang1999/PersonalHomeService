package com.charles.user.domain.service;

import com.charles.user.domain.model.User;
import com.charles.user.domain.repository.UserRepository;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Domain service containing pure business logic for user operations.
 * No framework dependencies - fully testable in isolation.
 */
public class UserDomainService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$"
    );

    private final UserRepository userRepository;

    public UserDomainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateRegistration(String email, String password) {
        validateEmail(email);
        validatePassword(password);
        checkEmailNotAlreadyRegistered(email);
    }

    public void validateEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Email format is invalid");
        }
    }

    public void validatePassword(String password) {
        if (password == null || !PASSWORD_PATTERN.matcher(password).matches()) {
            throw new IllegalArgumentException(
                    "Password must be at least 8 characters with uppercase, lowercase, and digits");
        }
    }

    public void checkEmailNotAlreadyRegistered(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalStateException("Email is already registered");
        }
    }

    public Optional<User> authenticate(String email, String password, String passwordHash) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return Optional.empty();
        }
        User user = userOpt.get();
        if (user.isDeleted() || user.isAccountDisabled()) {
            return Optional.empty();
        }
        return Optional.of(user);
    }
}
