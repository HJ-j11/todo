package com.example.todo.handler;

import com.example.todo.entity.error.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String accept = request.getHeader("Accept");
        if("application/json".equals(accept)) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.FORBIDDEN.name())
                    .message("Not Authorized")
                    .build();

            String result = objectMapper.writeValueAsString(errorResponse);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write(result);
        }
    }
}