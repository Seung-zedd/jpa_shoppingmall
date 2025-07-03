package com.mysite.jpa_shoppingmall.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class DataNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new DataNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public DataNotFoundException(String message) {
        super(message);
    }
}
