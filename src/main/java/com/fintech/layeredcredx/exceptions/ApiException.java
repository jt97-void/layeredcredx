package com.fintech.layeredcredx.exceptions;

import com.fintech.layeredcredx.common.enums.ErrorCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final ErrorCode code;

    public ApiException(ErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public ApiException(ErrorCode code, String customMessage) {
        super(code.getMessage());
        this.code = code;
    }

}
