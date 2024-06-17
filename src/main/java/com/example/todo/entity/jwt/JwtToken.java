package com.example.todo.entity.jwt;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtToken {
    private final String grantType;
    private final String accessToken;
    private final String refreshToken;
}