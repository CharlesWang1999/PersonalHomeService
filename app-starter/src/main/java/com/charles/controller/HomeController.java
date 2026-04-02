package com.charles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String home() {
        return "Hello";
    }

    @GetMapping("/health")
    public String health() {
        return "UP";
    }

    @GetMapping("/data")
    public String data() {
        String sql = "SELECT message FROM greeting ORDER BY id DESC LIMIT 1";
        String result = jdbcTemplate.queryForObject(sql, String.class);
        return result != null ? result : "";
    }
}
