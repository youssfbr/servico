package com.github.youssfbr.servicos.services;

import com.github.youssfbr.servicos.dto.TelephoneDTO;
import com.github.youssfbr.servicos.model.entities.Telephone;
import com.github.youssfbr.servicos.model.repositories.ITelephoneRepository;
import com.github.youssfbr.servicos.services.exceptions.DatabaseException;
import com.github.youssfbr.servicos.services.exceptions.ResourceNotFoundException;
import com.github.youssfbr.servicos.services.interfaces.ITelephoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TelephoneService implements ITelephoneService {

    private final ITelephoneRepository telephoneRepository;
    private static final String MESSAGE_ID = "Recurso não encontrado. Id: ";

    @Override
    @Transactional(readOnly = true)
    public List<TelephoneDTO> findAll() {
        List<Telephone> list = telephoneRepository.findAll();
        return list.stream().map(TelephoneDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TelephoneDTO findById(final Long id) {
        return telephoneRepository
                .findById(id)
                .map(TelephoneDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ID + id, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public TelephoneDTO persist(final TelephoneDTO dto) {

        Telephone entity = new Telephone();
        entity.setPhone(dto.getPhone());

        entity = telephoneRepository.save(entity);

        return new TelephoneDTO(entity);
    }

    @Override
    @Transactional
    public TelephoneDTO update(final Long id, final TelephoneDTO dto) {
        Telephone entity = telephoneRepository
                .findById(id)
                .map( entityUpdate -> {

                    entityUpdate.setPhone(dto.getPhone());

                    return telephoneRepository.save(entityUpdate);
                })
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ID + id, HttpStatus.NOT_FOUND));

        return new TelephoneDTO(entity);
    }

    @Override
    public void delete(final Long id) {
        try {
            telephoneRepository.deleteById(id);
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

}
