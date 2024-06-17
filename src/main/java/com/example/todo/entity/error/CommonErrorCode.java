package com.example.todo.entity.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not found"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Not authorized"),
    NOT_SUPPORTED(HttpStatus.METHOD_NOT_ALLOWED, "Unsupported HTTP method");

    private final HttpStatus httpStatus;
    private final String message;
}
