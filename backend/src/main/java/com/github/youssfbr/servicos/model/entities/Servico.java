package com.github.youssfbr.servicos.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @Column(nullable = false, length = 150)
    private String description;

    private BigDecimal price;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

}
