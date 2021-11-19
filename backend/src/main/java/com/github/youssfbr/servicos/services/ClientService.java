package com.github.youssfbr.servicos.services;

import com.github.youssfbr.servicos.dto.ClientDTO;
import com.github.youssfbr.servicos.model.entities.Client;
import com.github.youssfbr.servicos.model.repositories.IClientRepository;
import com.github.youssfbr.servicos.services.exceptions.DatabaseException;
import com.github.youssfbr.servicos.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ClientService implements IClientService {

    private final IClientRepository clientRepository;
    private static final String MESSAGE_ID = "Recurso não encontrado. Id: ";

    public ClientService(final IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDTO> findAll() {
        List<Client> list = clientRepository.findAll();

        return list.stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDTO findById(final Long id) {
        return clientRepository
                .findById(id)
                .map(ClientDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ID + id, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public ClientDTO persist(final ClientDTO dto) {

        Client entity = new Client();
        copyDtoToEntity(dto, entity);

        entity = clientRepository.save(entity);

        return new ClientDTO(entity);
    }

    @Override
    @Transactional
    public ClientDTO update(final Long id, final ClientDTO dto) {
        Client entity = clientRepository
                .findById(id)
                .map( entityUpdated -> {

                    entityUpdated = copyDtoToEntity(dto, entityUpdated);

                    return clientRepository.save(entityUpdated);
                })
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ID + id, HttpStatus.NOT_FOUND));

        return new ClientDTO(entity);
    }

    @Override
    public void delete(final Long id) {
        try {
            clientRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(MESSAGE_ID + id, HttpStatus.NOT_FOUND);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Recurso não deletado. Violação de integridade",
                    HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("Erro interno. Contate o suporte", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean validateDto(final Object object) {
        return Objects.nonNull(object) && !object.toString().isEmpty();
    }

    private Client copyDtoToEntity(final ClientDTO dto, final Client entity) {

        entity.setName(validateDto(dto.getName()) ? dto.getName() : entity.getName());
        entity.setCpf(validateDto(dto.getCpf()) ? dto.getCpf() : entity.getCpf());

        return entity;
    }
}
