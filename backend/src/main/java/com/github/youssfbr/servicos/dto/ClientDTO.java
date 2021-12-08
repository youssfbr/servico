package com.github.youssfbr.servicos.dto;

import com.github.youssfbr.servicos.model.entities.Client;
import com.github.youssfbr.servicos.model.entities.Telephone;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private Long id;

    @NotBlank(message = "{name.required}")
    @Size(max = 60)
    private String name;

    @NotBlank(message = "{cpf.required}")
    @CPF(message = "{cpf.invalid}")
    @Size(max = 11)
    private String cpf;

    @Email(message = "{email.invalid}")
    @Size(max = 60)
    private String email;

    private String birthDate;
    private String registerDate;
    private String note;

    private List<TelephoneDTO> telephones = new ArrayList<>();


    public ClientDTO(Client entity) {
        id = entity.getId();
        name = entity.getName();
        cpf = entity.getCpf();
        email = entity.getEmail();
        note = entity.getNote();

        if (entity.getBirthDate() != null) birthDate = convert(entity.getBirthDate());
        if (entity.getRegisterDate() != null) registerDate = convert(entity.getRegisterDate());

        entity.getTelephones().forEach(tel -> this.telephones.add(new TelephoneDTO(tel)));
    }

    public ClientDTO(Client entity, List<Telephone> telephones) {
        this(entity);
        telephones.forEach(tel -> this.telephones.add(new TelephoneDTO(tel)));
    }

    private static String convert(LocalDate dateIn) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateIn.format(formatters);
    }

}




