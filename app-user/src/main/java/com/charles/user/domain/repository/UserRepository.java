package com.charles.user.domain.repository;

import com.charles.user.domain.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface defined in domain layer (pure interface, no implementation).
 */
public interface UserRepository {

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    User save(User user);

    boolean existsByEmail(String email);

    List<User> findAll();
}
