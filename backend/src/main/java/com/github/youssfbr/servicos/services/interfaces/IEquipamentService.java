package com.github.youssfbr.servicos.services.interfaces;

import com.github.youssfbr.servicos.dto.EquipamentDTO;

import java.util.List;

public interface IEquipamentService {

    List<EquipamentDTO> findAll();
    EquipamentDTO findById(Long id);
    EquipamentDTO persist(final EquipamentDTO dto);
    EquipamentDTO update(Long id, EquipamentDTO dto);
    void delete(Long id);

}
