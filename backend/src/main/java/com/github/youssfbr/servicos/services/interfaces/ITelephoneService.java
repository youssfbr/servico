package com.github.youssfbr.servicos.services.interfaces;

import com.github.youssfbr.servicos.dto.TelephoneDTO;

import java.util.List;

public interface ITelephoneService {

    List<TelephoneDTO> findAll();
    TelephoneDTO findById(Long id);
    TelephoneDTO persist(final TelephoneDTO dto);
    TelephoneDTO update(Long id, TelephoneDTO dto);
    void delete(Long id);

}
