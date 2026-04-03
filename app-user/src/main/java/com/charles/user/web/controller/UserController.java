package com.charles.user.web.controller;

import com.charles.user.application.dto.PagedResponse;
import com.charles.user.application.dto.UserDetailResponse;
import com.charles.user.application.service.UserApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for admin user management endpoints.
 */
@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @GetMapping
    public PagedResponse<UserDetailResponse> listUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return userApplicationService.listUsers(page, size);
    }

    @GetMapping("/{id}")
    public UserDetailResponse getUserDetail(@PathVariable String id) {
        return userApplicationService.getUserDetail(id);
    }

    @PatchMapping("/{id}/disable")
    public UserDetailResponse disableUser(@PathVariable String id) {
        return userApplicationService.disableUser(id);
    }

    @PatchMapping("/{id}/enable")
    public UserDetailResponse enableUser(@PathVariable String id) {
        return userApplicationService.enableUser(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        userApplicationService.deleteUser(id);
    }
}
