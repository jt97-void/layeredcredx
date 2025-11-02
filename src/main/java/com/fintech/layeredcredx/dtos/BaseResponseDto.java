package com.fintech.layeredcredx.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;

@Data
@Builder
public class BaseResponseDto<T> {

    private static final String SUCCESS = "SUCCESS";

    private boolean isSuccess;
    private String code;
    private String message;
    private T data;
    private List<String> errors;
    private LocalDateTime timestamp;

    public static <T> BaseResponseDto<T> success(T data) {
        return BaseResponseDto.<T>builder()
                .isSuccess(true)
                .code(SUCCESS)
                .data(data)
                .timestamp(now())
                .build();
    }

    public static <T> BaseResponseDto<T> failure(String code, String message) {
        return BaseResponseDto.<T>builder()
                .isSuccess(false)
                .code(code)
                .message(message)
                .timestamp(now())
                .build();
    }

}
