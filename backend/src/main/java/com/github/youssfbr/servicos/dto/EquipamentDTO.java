package com.github.youssfbr.servicos.dto;

import com.github.youssfbr.servicos.model.entities.Brand;
import com.github.youssfbr.servicos.model.entities.Client;
import com.github.youssfbr.servicos.model.entities.Equipament;
import com.github.youssfbr.servicos.model.entities.enums.EquipamentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipamentDTO {

    private Long id;

    private EquipamentType equipamentType;

    private Client client;

    private Brand brand;

    @Size(max = 30)
    private String model;

    @Size(max = 60)
    private String serial;

    private String registerDate;
    private String note;

    public EquipamentDTO(Equipament entity) {
        id = entity.getId();
        equipamentType = entity.getEquipamentType();
        client = entity.getClient();
        brand = entity.getBrand();
        model = entity.getModel();
        serial = entity.getSerial();
        note = entity.getNote();

        if (entity.getRegisterDate() != null) registerDate = convert(entity.getRegisterDate());
    }

    private static String convert(LocalDate dateIn) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateIn.format(formatters);
    }

}
