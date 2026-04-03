package com.charles.user.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * JPA configuration for the user module.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.charles.user.infrastructure.persistence")
@EnableTransactionManagement
public class JpaConfig {
}
