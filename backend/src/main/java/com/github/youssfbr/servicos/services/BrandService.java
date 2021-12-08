package com.github.youssfbr.servicos.services;

import com.github.youssfbr.servicos.dto.BrandDTO;
import com.github.youssfbr.servicos.model.entities.Brand;
import com.github.youssfbr.servicos.model.repositories.IBrandRepository;
import com.github.youssfbr.servicos.services.exceptions.DatabaseException;
import com.github.youssfbr.servicos.services.exceptions.ResourceNotFoundException;
import com.github.youssfbr.servicos.services.interfaces.IBrandService;
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
public class BrandService implements IBrandService {

    private final IBrandRepository brandRepository;
    private static final String MESSAGE_ID = "Recurso não encontrado. Id: ";

    @Override
    @Transactional(readOnly = true)
    public List<BrandDTO> findAll() {
        List<Brand> list = brandRepository.findAll();
        return list.stream().map(BrandDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BrandDTO findById(final Long id) {
        return brandRepository
                .findById(id)
                .map(BrandDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ID + id, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public BrandDTO persist(final BrandDTO dto) {

        Brand entity = new Brand();
        entity.setName(dto.getName());

        entity = brandRepository.save(entity);

        return new BrandDTO(entity);
    }

    @Override
    @Transactional
    public BrandDTO update(final Long id, final BrandDTO dto) {
        Brand entity = brandRepository
                .findById(id)
                .map( entityUpdated -> {

                    entityUpdated.setName(dto.getName());

                    return brandRepository.save(entityUpdated);
                })
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ID + id, HttpStatus.NOT_FOUND));

        return new BrandDTO(entity);
    }

    @Override
    public void delete(final Long id) {
        try {
            brandRepository.deleteById(id);
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
