package com.example.todo.handler;

import com.example.todo.dto.api.ApiResponse;
import com.example.todo.entity.error.CommonErrorCode;
import com.example.todo.entity.error.ErrorCode;
import com.example.todo.entity.error.ErrorResponse;
import com.example.todo.entity.exception.RestApiException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RestApiException.class)
    public ApiResponse<Void> handleCustomException(RestApiException e) {
        ErrorCode errorCode = e.getErrorCode();
        return ApiResponse.createError(errorCode);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<Void> handleIllegalArgument(IllegalArgumentException e) {
        log.warn("IllegalArgument ",e);
        ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
        return ApiResponse.createError(errorCode);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorCode errorCode = CommonErrorCode.RESOURCE_NOT_FOUND;
        log.warn("Not Found Exception ", ex);
         return handleExceptionInternal(errorCode);
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleAllException(Exception ex) {
        log.warn("handleAllException", ex);
        ErrorCode errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;
        return ApiResponse.createError(errorCode);
    }

    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode));
    }

    private ErrorResponse makeErrorResponse(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .status(errorCode.name())
                .message(errorCode.getMessage())
                .build();
    }
}
