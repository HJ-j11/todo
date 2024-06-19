package com.example.todo.entity.jwt;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class jwtResponse {
    private final String grantType;
    private final String accessToken;
    private final String refreshToken;
    private String username;
    private String name;
    private List<String> roles;
}