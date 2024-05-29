package com.example.todo.entity.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

    INACTIVE_USER(HttpStatus.BAD_REQUEST, "User is inactive"),;

    private final HttpStatus httpStatus;
    private final String message;
}
