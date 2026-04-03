package com.charles.user.application.service;

import com.charles.user.application.dto.*;
import com.charles.user.application.port.PasswordEncoder;
import com.charles.user.domain.model.User;
import com.charles.user.domain.repository.UserRepository;
import com.charles.user.domain.service.UserDomainService;
import com.charles.user.infrastructure.persistence.UserJpaRepository;
import com.charles.user.infrastructure.security.JwtTokenProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Application service orchestrating user-related use cases.
 */
@Service
public class UserApplicationService {

    private final UserRepository userRepository;
    private final UserJpaRepository userJpaRepository;
    private final UserDomainService userDomainService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserApplicationService(UserRepository userRepository,
                                  UserJpaRepository userJpaRepository,
                                  UserDomainService userDomainService,
                                  PasswordEncoder passwordEncoder,
                                  JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.userJpaRepository = userJpaRepository;
        this.userDomainService = userDomainService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        userDomainService.validateRegistration(request.getEmail(), request.getPassword());

        String id = UUID.randomUUID().toString();
        String passwordHash = passwordEncoder.encode(request.getPassword());
        Instant now = Instant.now();

        User user = new User(id, request.getEmail(), passwordHash, false, now, now, false);
        userRepository.save(user);

        return new RegisterResponse(id, request.getEmail());
    }

    public LoginResponse login(LoginRequest request) {
        User user = userDomainService.authenticate(request.getEmail(), request.getPassword(), null)
                .orElseThrow(() -> new IllegalArgumentException("Email or password is incorrect"));

        String accessToken = jwtTokenProvider.generateAccessToken(user.getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        return new LoginResponse(accessToken, refreshToken, 3600);
    }

    public LoginResponse refreshToken(RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();

        if (!jwtTokenProvider.validateToken(refreshToken) || jwtTokenProvider.isTokenExpired(refreshToken)) {
            throw new IllegalArgumentException("Refresh token is expired");
        }

        String userId = jwtTokenProvider.getUserIdFromToken(refreshToken);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.isDeleted() || user.isAccountDisabled()) {
            throw new IllegalArgumentException("User account is not active");
        }

        String newAccessToken = jwtTokenProvider.generateAccessToken(userId);
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(userId);

        return new LoginResponse(newAccessToken, newRefreshToken, 3600);
    }

    @Transactional(readOnly = true)
    public PagedResponse<UserDetailResponse> listUsers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        var userPage = userJpaRepository.findAll(pageRequest);

        var content = userPage.getContent().stream()
                .map(entity -> toUserDetailResponse(entity.toDomain()))
                .collect(Collectors.toList());

        return new PagedResponse<>(content, page, size, userPage.getTotalElements());
    }

    @Transactional(readOnly = true)
    public UserDetailResponse getUserDetail(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return toUserDetailResponse(user);
    }

    @Transactional
    public UserDetailResponse disableUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        User disabledUser = user.withAccountDisabled(true);
        userRepository.save(disabledUser);
        return toUserDetailResponse(disabledUser);
    }

    @Transactional
    public UserDetailResponse enableUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        User enabledUser = user.withAccountDisabled(false);
        userRepository.save(enabledUser);
        return toUserDetailResponse(enabledUser);
    }

    @Transactional
    public void deleteUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        User deletedUser = user.withDeleted(true);
        userRepository.save(deletedUser);
    }

    private UserDetailResponse toUserDetailResponse(User user) {
        return new UserDetailResponse(
                user.getId(),
                user.getEmail(),
                user.isAccountDisabled(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
