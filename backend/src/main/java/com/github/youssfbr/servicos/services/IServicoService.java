package com.github.youssfbr.servicos.services;

import com.github.youssfbr.servicos.dto.ServicoDTO;

import java.util.List;

public interface IServicoService {

    List<ServicoDTO> findAll();
    ServicoDTO findById(Long id);
    ServicoDTO persist(final ServicoDTO dto);
    ServicoDTO update(Long id, ServicoDTO dto);
    void delete(Long id);

}
