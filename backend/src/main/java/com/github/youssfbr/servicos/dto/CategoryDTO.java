package com.github.youssfbr.servicos.dto;

import com.github.youssfbr.servicos.model.entities.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long id;

    @Size(max = 20)
    private String name;

    public CategoryDTO(Category entity) {
        id = entity.getId();
        name = entity.getName();
    }

}
