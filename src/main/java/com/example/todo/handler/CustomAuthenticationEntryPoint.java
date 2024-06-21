package com.example.todo.handler;

import com.example.todo.entity.error.CommonErrorCode;
import com.example.todo.entity.error.ErrorCode;
import com.example.todo.entity.error.ErrorResponse;
import com.example.todo.entity.error.UserErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
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
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String exception = (String) request.getAttribute("exception");
        if(exception.isEmpty()) {
            setResponse(response, CommonErrorCode.INTERNAL_SERVER_ERROR);
        } else if(exception.equals(UserErrorCode.INVALID_TOKEN.getMessage())) {
            setResponse(response, UserErrorCode.INVALID_TOKEN);
        } else if(exception.equals(UserErrorCode.EXPIRED_TOKEN.getMessage())) {
            setResponse(response, UserErrorCode.EXPIRED_TOKEN);
        }
    }

    private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(errorCode.getHttpStatus().name())
                .message(errorCode.getMessage())
                .build();

        String result = objectMapper.writeValueAsString(errorResponse);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(result);
    }
}