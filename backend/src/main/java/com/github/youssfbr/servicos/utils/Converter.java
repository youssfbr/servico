package com.github.youssfbr.servicos.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class Converter {

    public BigDecimal converterBigDecimal(String value) {

        if (value == null) return null;

        value = value
                .replace(".", "")
                .replace(",", ".");

        return new BigDecimal(value);
    }


    public LocalDate converterLocalDate(String value) {

        return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
