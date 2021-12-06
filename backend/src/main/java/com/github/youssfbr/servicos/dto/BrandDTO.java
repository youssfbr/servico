package com.github.youssfbr.servicos.dto;

import com.github.youssfbr.servicos.model.entities.Brand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandDTO {

    private Long id;
    private String name;

    public BrandDTO(Brand entity) {
        id = entity.getId();
        name = entity.getName();
    }

}
