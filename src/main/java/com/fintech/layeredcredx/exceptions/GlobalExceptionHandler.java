package com.fintech.layeredcredx.exceptions;


import com.fintech.layeredcredx.dtos.BaseResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.fintech.layeredcredx.common.enums.ErrorCode.INTERNAL_ERROR;
import static com.fintech.layeredcredx.common.enums.ErrorCode.INVALID_INPUT;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<BaseResponseDto<Void>> handleApiException(ApiException ex) {
        log.error("API Error : {}", ex.getMessage());

        var code = ex.getCode();
        var errorMessage = ex.getMessage();

        return ResponseEntity
                .status(code.getStatus())
                .body(BaseResponseDto.failure(code.name(), errorMessage));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponseDto<Void>> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> String.format("%s: %s", err.getField(), err.getDefaultMessage()))
                .toList();

        var aggregatedMsg = String.format("Validation Failed for %d field(s)", errors.size());
        log.error(aggregatedMsg);

        return ResponseEntity
                .status(INVALID_INPUT.getStatus())
                .body(BaseResponseDto.<Void>builder()
                        .isSuccess(false)
                        .message(aggregatedMsg)
                        .errors(errors)
                        .timestamp(now())
                        .build());
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<BaseResponseDto<Void>> handleIllegalExceptions(RuntimeException ex) {
        log.error("Illegal operation : {}", ex.getMessage(), ex);

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(BaseResponseDto.failure(INVALID_INPUT.name(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponseDto<Void>> handleGenericException(Exception ex) {
        log.error("Unhandled exception occurred: ", ex);

        return ResponseEntity
                .status(INTERNAL_ERROR.getStatus())
                .body(BaseResponseDto.failure(INTERNAL_ERROR.name(), INTERNAL_ERROR.getMessage()));
    }

}
