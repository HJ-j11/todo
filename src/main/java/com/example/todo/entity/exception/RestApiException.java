package com.example.todo.entity.exception;

import com.example.todo.entity.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class RestApiException extends RuntimeException {
    private final ErrorCode errorCode;

    public RestApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
