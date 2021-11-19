package com.github.youssfbr.servicos.dto;

import com.github.youssfbr.servicos.model.entities.Client;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private Long id;

    @NotBlank
    @Size(max = 60)
    private String name;

    @NotNull
    @CPF
    @Size(max = 11)
    private String cpf;

    private String registerDate;

    public ClientDTO(Client entity) {
        id = entity.getId();
        name = entity.getName();
        cpf = entity.getCpf();
        registerDate = convert(entity.getRegisterDate());
    }

    private static String convert(LocalDate dateIn) {

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return dateIn.format(formatters);
    }

}
