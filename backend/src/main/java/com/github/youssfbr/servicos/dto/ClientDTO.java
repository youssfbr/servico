package com.github.youssfbr.servicos.dto;

import com.github.youssfbr.servicos.model.entities.Client;
import lombok.*;

import java.time.LocalDate;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private Long id;
    private String name;
    private String cpf;
    private LocalDate registerDate;

    public ClientDTO(Client entity) {
        id = entity.getId();
        name = entity.getName();
        cpf = entity.getCpf();
        registerDate = entity.getRegisterDate();
    }
}
