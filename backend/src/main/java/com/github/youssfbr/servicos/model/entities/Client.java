package com.github.youssfbr.servicos.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(length = 60)
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    private LocalDate registerDate;

    @Column(columnDefinition = "TEXT")
    private String note;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="client_id")
    private List<Telephone> telephones = new ArrayList<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private List<Equipament> equipaments = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        setRegisterDate(LocalDate.now());
    }

}
