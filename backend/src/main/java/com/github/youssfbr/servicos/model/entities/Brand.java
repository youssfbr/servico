package com.github.youssfbr.servicos.model.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

}
