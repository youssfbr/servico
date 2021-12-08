package com.github.youssfbr.servicos.dto;

import com.github.youssfbr.servicos.model.entities.Telephone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelephoneDTO {

    private Long id;
    private String phone;

    public TelephoneDTO(Telephone entity) {
        id = entity.getId();
        phone = entity.getPhone();
    }

}
