package com.github.youssfbr.servicos.dto;

import com.github.youssfbr.servicos.model.entities.Client;
import com.github.youssfbr.servicos.model.entities.Servico;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServicoDTO {

    private String id;

    @NotBlank(message = "{description.required}")
    private String description;

    @NotBlank(message = "{price.required}")
    private String price;

    @NotBlank(message = "{date.required}")
    private String date;

    @NotNull(message = "{cliente.required}")
    private Long clientId;
    private Client client;

    public ServicoDTO(Servico entity) {
        id = entity.getId().toString();

        clientId = entity.getClient().getId();
        client = entity.getClient();

        description = entity.getDescription();
        price = entity.getPrice().toString();
        date = convert(entity.getDate());
    }

    private static String convert(LocalDate dateIn) {

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return dateIn.format(formatters);
    }
}
