package com.github.youssfbr.servicos.rest;

import com.github.youssfbr.servicos.dto.ServicoDTO;
import com.github.youssfbr.servicos.services.IServicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    private final IServicoService servicoService;

    public ServicoController(final IServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public ResponseEntity<List<ServicoDTO>> findAll() {
        return ResponseEntity.ok(servicoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(servicoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ServicoDTO> persist(@RequestBody @Valid ServicoDTO dto) {

        dto = servicoService.persist(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoDTO> update(@PathVariable Long id, @RequestBody @Valid ServicoDTO dto) {

        dto = servicoService.update(id, dto);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        servicoService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
