package com.github.youssfbr.servicos.rest.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter@Setter
@NoArgsConstructor
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
    private List<Field> fields;

    @AllArgsConstructor
    @Getter@Setter
    public static  class Field {
        private String fieldError;
        private String message;
    }
}
