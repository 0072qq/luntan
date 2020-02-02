package com.community.test.exception;

import lombok.Data;

@Data
public class CustomizeException extends RuntimeException {
    private Integer code;
    private String message;

    public CustomizeException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomizeException(String message) {
        this.message = message;
    }
}
