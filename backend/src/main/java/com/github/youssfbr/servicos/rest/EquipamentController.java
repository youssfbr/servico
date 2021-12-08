package com.github.youssfbr.servicos.rest;

import com.github.youssfbr.servicos.dto.EquipamentDTO;
import com.github.youssfbr.servicos.services.interfaces.IEquipamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/equipaments")
@RequiredArgsConstructor
public class EquipamentController {

    private final IEquipamentService equipamentService;

    @GetMapping
    public ResponseEntity<List<EquipamentDTO>> findAll() {
        return ResponseEntity.ok(equipamentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipamentDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(equipamentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EquipamentDTO> persist(@RequestBody EquipamentDTO dto) {

        dto = equipamentService.persist(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipamentDTO> update(@PathVariable Long id, @RequestBody EquipamentDTO dto) {

        dto = equipamentService.update(id, dto);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        equipamentService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
