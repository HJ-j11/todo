package com.example.todo.handler;

import com.example.todo.entity.error.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String accept = request.getHeader("Accept");

        if("application/json".equals(accept)) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.UNAUTHORIZED.name())
                    .message("Need Authentication")
                    .build();

            String result = objectMapper.writeValueAsString(errorResponse);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(401);
            response.getWriter().write(result);
        }
    }
}