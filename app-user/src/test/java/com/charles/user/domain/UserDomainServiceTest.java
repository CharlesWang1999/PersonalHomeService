package com.charles.user.domain;

import com.charles.user.domain.model.User;
import com.charles.user.domain.repository.UserRepository;
import com.charles.user.domain.service.UserDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for UserDomainService.
 * These tests run without Spring or any framework dependencies.
 */
@ExtendWith(MockitoExtension.class)
class UserDomainServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserDomainService userDomainService;

    @BeforeEach
    void setUp() {
        userDomainService = new UserDomainService(userRepository);
    }

    @Test
    void validateEmail_withValidEmail_shouldNotThrow() {
        assertDoesNotThrow(() -> userDomainService.validateEmail("test@example.com"));
    }

    @Test
    void validateEmail_withInvalidEmail_shouldThrow() {
        assertThrows(IllegalArgumentException.class,
            () -> userDomainService.validateEmail("invalid-email"));
    }

    @Test
    void validatePassword_withValidPassword_shouldNotThrow() {
        assertDoesNotThrow(() -> userDomainService.validatePassword("Password123"));
    }

    @Test
    void validatePassword_withTooShort_shouldThrow() {
        assertThrows(IllegalArgumentException.class,
            () -> userDomainService.validatePassword("Pass1"));
    }

    @Test
    void validatePassword_withoutUppercase_shouldThrow() {
        assertThrows(IllegalArgumentException.class,
            () -> userDomainService.validatePassword("password123"));
    }

    @Test
    void validatePassword_withoutLowercase_shouldThrow() {
        assertThrows(IllegalArgumentException.class,
            () -> userDomainService.validatePassword("PASSWORD123"));
    }

    @Test
    void validatePassword_withoutDigit_shouldThrow() {
        assertThrows(IllegalArgumentException.class,
            () -> userDomainService.validatePassword("Passwordabc"));
    }

    @Test
    void checkEmailNotAlreadyRegistered_whenEmailExists_shouldThrow() {
        when(userRepository.existsByEmail("existing@example.com")).thenReturn(true);

        assertThrows(IllegalStateException.class,
            () -> userDomainService.checkEmailNotAlreadyRegistered("existing@example.com"));
    }

    @Test
    void checkEmailNotAlreadyRegistered_whenEmailNotExists_shouldNotThrow() {
        when(userRepository.existsByEmail("new@example.com")).thenReturn(false);

        assertDoesNotThrow(() -> userDomainService.checkEmailNotAlreadyRegistered("new@example.com"));
    }

    @Test
    void authenticate_withValidCredentials_shouldReturnUser() {
        String email = "test@example.com";
        Instant now = Instant.now();
        User user = new User("id-1", email, "hashedPassword", false, now, now, false);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<User> result = userDomainService.authenticate(email, "password", "hashedPassword");

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void authenticate_withNonExistentEmail_shouldReturnEmpty() {
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        Optional<User> result = userDomainService.authenticate("nonexistent@example.com", "password", null);

        assertTrue(result.isEmpty());
    }

    @Test
    void authenticate_withDisabledAccount_shouldReturnEmpty() {
        String email = "disabled@example.com";
        Instant now = Instant.now();
        User user = new User("id-1", email, "hashedPassword", true, now, now, false);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<User> result = userDomainService.authenticate(email, "password", "hashedPassword");

        assertTrue(result.isEmpty());
    }

    @Test
    void authenticate_withDeletedAccount_shouldReturnEmpty() {
        String email = "deleted@example.com";
        Instant now = Instant.now();
        User user = new User("id-1", email, "hashedPassword", false, now, now, true);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<User> result = userDomainService.authenticate(email, "password", "hashedPassword");

        assertTrue(result.isEmpty());
    }
}
