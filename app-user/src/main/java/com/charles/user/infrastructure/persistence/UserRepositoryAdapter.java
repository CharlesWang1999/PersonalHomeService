package com.charles.user.infrastructure.persistence;

import com.charles.user.domain.model.User;
import com.charles.user.domain.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adapter implementing domain UserRepository using JPA.
 * Lives in infrastructure layer as it deals with persistence technology.
 */
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;

    public UserRepositoryAdapter(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<User> findById(String id) {
        return jpaRepository.findById(id)
                .filter(e -> !e.isDeleted())
                .map(this::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmailAndDeletedFalse(email)
                .map(this::toDomain);
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = toEntity(user);
        return toDomain(jpaRepository.save(entity));
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmailAndDeletedFalse(email);
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll().stream()
                .filter(e -> !e.isDeleted())
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private UserJpaEntity toEntity(User user) {
        return new UserJpaEntity(
                user.getId(),
                user.getEmail(),
                user.getPasswordHash(),
                user.isAccountDisabled(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.isDeleted()
        );
    }

    private User toDomain(UserJpaEntity entity) {
        return entity.toDomain();
    }
}
