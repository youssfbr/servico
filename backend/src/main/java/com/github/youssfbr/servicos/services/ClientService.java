package com.github.youssfbr.servicos.services;

import com.github.youssfbr.servicos.model.entities.Client;
import com.github.youssfbr.servicos.model.repositories.IClientRepository;
import com.github.youssfbr.servicos.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService implements IClientService {

    private final IClientRepository clientRepository;
    private static final String MESSAGE_ID = "Recurso não encontrado. Id: ";

    public ClientService(final IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ID + id, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public Client persist(Client client) {
        return clientRepository.save(client);
    }
}
