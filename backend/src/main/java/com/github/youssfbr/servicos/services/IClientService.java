package com.github.youssfbr.servicos.services;

import com.github.youssfbr.servicos.dto.ClientDTO;

import java.util.List;

public interface IClientService {

    List<ClientDTO> findAll();
    ClientDTO findById(Long id);
    ClientDTO persist(final ClientDTO dto);
    ClientDTO update(Long id, ClientDTO dto);
    void delete(Long id);

}
