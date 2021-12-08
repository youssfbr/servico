package com.github.youssfbr.servicos.services;

import com.github.youssfbr.servicos.dto.EquipamentDTO;
import com.github.youssfbr.servicos.model.entities.Brand;
import com.github.youssfbr.servicos.model.entities.Client;
import com.github.youssfbr.servicos.model.entities.Equipament;
import com.github.youssfbr.servicos.model.repositories.IBrandRepository;
import com.github.youssfbr.servicos.model.repositories.IClientRepository;
import com.github.youssfbr.servicos.model.repositories.IEquipamentRepository;
import com.github.youssfbr.servicos.services.exceptions.DatabaseException;
import com.github.youssfbr.servicos.services.exceptions.ResourceNotFoundException;
import com.github.youssfbr.servicos.services.interfaces.IEquipamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipamentService implements IEquipamentService {

    private final IEquipamentRepository equipamentRepository;
    private final IClientRepository clientRepository;
    private final IBrandRepository brandRepository;
    private static final String MESSAGE_ID = "Recurso não encontrado. Id: ";

    @Override
    @Transactional(readOnly = true)
    public List<EquipamentDTO> findAll() {
        List<Equipament> list = equipamentRepository.findAll();
        return list.stream().map(EquipamentDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EquipamentDTO findById(final Long id) {
        return equipamentRepository
                .findById(id)
                .map(EquipamentDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ID + id, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public EquipamentDTO persist(final EquipamentDTO dto) {

        Equipament entity = new Equipament();
        copyDtoToEntity(dto, entity);

        entity = equipamentRepository.save(entity);

        return new EquipamentDTO(entity);
    }

    @Override
    @Transactional
    public EquipamentDTO update(final Long id, final EquipamentDTO dto) {
        Equipament entity = equipamentRepository
                .findById(id)
                .map( entityUpdated -> {

                    entityUpdated = copyDtoToEntity(dto, entityUpdated);

                    return equipamentRepository.save(entityUpdated);
                })
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ID + id, HttpStatus.NOT_FOUND));

        return new EquipamentDTO(entity);
    }

    @Override
    public void delete(final Long id) {
        try {
            equipamentRepository.deleteById(id);
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

    private Equipament copyDtoToEntity(final EquipamentDTO dto, final Equipament entity) {

        entity.setEquipamentType(validateDto(dto.getEquipamentType()) ? dto.getEquipamentType() : entity.getEquipamentType());

        Client client = clientRepository
                .findById(dto.getClient().getId())
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ID + dto.getClient().getId(), HttpStatus.NOT_FOUND));
        entity.setClient(client);

        Brand brand = brandRepository
                .findById(dto.getBrand().getId())
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ID + dto.getBrand().getId(), HttpStatus.NOT_FOUND));
        entity.setBrand(brand);

        entity.setModel(validateDto(dto.getModel()) ? dto.getModel() : entity.getModel());
        entity.setSerial(validateDto(dto.getSerial()) ? dto.getSerial() : entity.getSerial());
        entity.setNote(validateDto(dto.getModel()) ? dto.getModel() : entity.getModel());

        return entity;
    }
}
