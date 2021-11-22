package com.github.youssfbr.servicos.services;

import com.github.youssfbr.servicos.dto.ServicoDTO;
import com.github.youssfbr.servicos.model.entities.Client;
import com.github.youssfbr.servicos.model.entities.Servico;
import com.github.youssfbr.servicos.model.repositories.IClientRepository;
import com.github.youssfbr.servicos.model.repositories.IServicoRepository;
import com.github.youssfbr.servicos.services.exceptions.DatabaseException;
import com.github.youssfbr.servicos.services.exceptions.ResourceNotFoundException;
import com.github.youssfbr.servicos.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicoService implements IServicoService {

    private final IServicoRepository servicoRepository;
    private final IClientRepository clienteRepository;

    private final Converter converter;

    private static final String MESSAGE_ID = "Recurso não encontrado. Id: ";

    @Override
    @Transactional(readOnly = true)
    public List<ServicoDTO> findAll() {
        List<Servico> list = servicoRepository.findAll();

        return list.stream().map(ServicoDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicoDTO> find(String name, Integer month) {
        List<Servico> list = servicoRepository.findByNameClientAndMounth("%" + name + "%", month);

        return list.stream().map(ServicoDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ServicoDTO findById(final Long id) {
        return servicoRepository
                .findById(id)
                .map(ServicoDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ID + id, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public ServicoDTO persist(final ServicoDTO dto) {

        Servico entity = new Servico();
        copyDtoToEntity(dto, entity);

        entity = servicoRepository.save(entity);

        return new ServicoDTO(entity);
    }

    @Override
    @Transactional
    public ServicoDTO update(final Long id, final ServicoDTO dto) {
        Servico entity = servicoRepository
                .findById(id)
                .map( entityUpdate -> {

                    Servico servico = copyDtoToEntity(dto, entityUpdate);

                    return servicoRepository.save(servico);
                })
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ID + id, HttpStatus.NOT_FOUND));

        return new ServicoDTO(entity);
    }

    @Override
    public void delete(final Long id) {
        try {
            servicoRepository.deleteById(id);
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

    private Servico copyDtoToEntity(final ServicoDTO dto, final Servico entity) {

        LocalDate data = LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Long clienteId = dto.getClientId();
        Client client = clienteRepository
                        .findById(clienteId)
                        .orElseThrow(() -> new ResourceNotFoundException("Cliente inexistente", HttpStatus.NOT_FOUND));

        entity.setId(validateDto(dto.getId()) ? Long.valueOf(dto.getId()) : entity.getId());
        entity.setClient(validateDto(client) ? client : entity.getClient());
        entity.setDescription(validateDto(dto.getDescription()) ? dto.getDescription() : entity.getDescription());
        entity.setPrice(validateDto(converter.converterBigDecimal(dto.getPrice())) ? converter.converterBigDecimal(dto.getPrice()) : entity.getPrice());
        entity.setDate(validateDto(data) ? data : entity.getDate());

        return entity;
    }
}
