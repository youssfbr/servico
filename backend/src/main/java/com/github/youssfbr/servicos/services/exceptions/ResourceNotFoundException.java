package com.github.youssfbr.servicos.services.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final HttpStatus httpStatus;

    public ResourceNotFoundException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
