package com.github.youssfbr.servicos.rest.exceptions;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class FieldsErrors {

    @Getter
    private List<String> errors;

    public FieldsErrors(List<String> errors) {
        this.errors = errors;
    }

    public FieldsErrors(String message) {
        this.errors = Arrays.asList(message);
    }
}
