package com.github.youssfbr.servicos.dto;

import com.github.youssfbr.servicos.model.entities.Client;
import com.github.youssfbr.servicos.model.entities.Servico;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServicoDTO {

    private String id;
    private String description;
    private String price;
    private String date;

    private Long clienteId;
    private Client client;


    public ServicoDTO(Servico entity) {
        id = entity.getId().toString();

        clienteId = entity.getClient().getId();
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
