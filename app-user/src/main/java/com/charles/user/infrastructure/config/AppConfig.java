package com.charles.user.infrastructure.config;

import com.charles.user.application.port.PasswordEncoder;
import com.charles.user.domain.repository.UserRepository;
import com.charles.user.domain.service.UserDomainService;
import com.charles.user.infrastructure.persistence.UserJpaRepository;
import com.charles.user.infrastructure.persistence.UserRepositoryAdapter;
import com.charles.user.infrastructure.security.BcryptPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Application configuration for dependency injection.
 */
@Configuration
public class AppConfig {

    @Bean
    public UserRepository userRepository(UserJpaRepository jpaRepository) {
        return new UserRepositoryAdapter(jpaRepository);
    }

    @Bean
    public UserDomainService userDomainService(UserRepository userRepository) {
        return new UserDomainService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BcryptPasswordEncoder();
    }
}
