package com.example.todo.dto.api;

import com.example.todo.entity.error.ErrorCode;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ApiResponse <T> {
    private static final String SUCCESS_STATUS = "SUCCESS";
    private static final String FAIL_STATUS = "FAIL";
    private static final String ERROR_STATUS = "ERROR";

    private String status;
    private String message;
    private T response;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(SUCCESS_STATUS, HttpStatus.OK.getReasonPhrase(), data );
    }

    public static <T> ApiResponse<T> successWithoutContent() {
        return new ApiResponse<>(SUCCESS_STATUS, HttpStatus.OK.getReasonPhrase(), null);
    }

    public static <T> ApiResponse<T> successCreated() {
        return new ApiResponse<>(SUCCESS_STATUS, HttpStatus.CREATED.getReasonPhrase(), null);
    }

    public static <T> ApiResponse<T> createError(ErrorCode errorCode) {
        return new ApiResponse<>(ERROR_STATUS, errorCode.getMessage(), null);
    }
}
