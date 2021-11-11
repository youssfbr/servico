package com.github.youssfbr.servicos.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 11)
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate registerDate;

    @PrePersist
    public void prePersist() {
        setRegisterDate(LocalDate.now());
    }

}
