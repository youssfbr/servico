package com.github.youssfbr.servicos.services;

import com.github.youssfbr.servicos.model.entities.Client;

import java.util.List;

public interface IClientService {

    List<Client> findAll();
    Client findById(Long id);
    Client persist(final Client entity);

}
