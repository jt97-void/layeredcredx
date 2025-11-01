package com.fintech.layeredcredx.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
//@AllArgsConstructor
public enum ErrorCode {

    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "Account not found."),
    TXN_NOT_FOUND(HttpStatus.NOT_FOUND, "Transaction not found."),

    INVALID_INPUT(HttpStatus.BAD_REQUEST, "One or more request parameters are invalid."),
    MISSING_FIELD(HttpStatus.BAD_REQUEST, "A required field is missing."),
    MALFORMED_REQUEST(HttpStatus.BAD_REQUEST, "The request body could not be parsed."),
    INVALID_ENUM_VALUE(HttpStatus.BAD_REQUEST, "Invalid or unsupported value supplied."),

    BUSINESS_RULE_VIOLATION(HttpStatus.UNPROCESSABLE_ENTITY, "Request violates a business rule."),

    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
