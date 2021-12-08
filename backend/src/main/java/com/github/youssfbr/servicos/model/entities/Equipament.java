package com.github.youssfbr.servicos.model.entities;

import com.github.youssfbr.servicos.model.entities.enums.EquipamentType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
public class Equipament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private EquipamentType equipamentType;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Brand brand;

    @Column(length = 30)
    private String model;

    @Column(length = 30)
    private String serial;

    private LocalDate registerDate;

    @Column(columnDefinition = "TEXT")
    private String note;

    @PrePersist
    public void prePersist() {
        setRegisterDate(LocalDate.now());
    }

}
