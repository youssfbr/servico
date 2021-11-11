package com.github.youssfbr.servicos.services;

import com.github.youssfbr.servicos.model.entities.Client;
import com.github.youssfbr.servicos.model.repositories.IClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService implements IClientService {

    private final IClientRepository clientRepository;

    public ClientService(final IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional
    public Client persist(Client client) {
        return clientRepository.save(client);
    }
}
