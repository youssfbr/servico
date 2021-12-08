package com.github.youssfbr.servicos.rest;

import com.github.youssfbr.servicos.dto.TelephoneDTO;
import com.github.youssfbr.servicos.services.interfaces.ITelephoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/telephones")
@RequiredArgsConstructor
public class TelephoneController {

    private final ITelephoneService telephoneService;

    @GetMapping
    public ResponseEntity<List<TelephoneDTO>> findAll() {
        return ResponseEntity.ok(telephoneService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelephoneDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(telephoneService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TelephoneDTO> persist(@RequestBody TelephoneDTO dto) {

        dto = telephoneService.persist(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelephoneDTO> update(@PathVariable Long id, @RequestBody TelephoneDTO dto) {

        dto = telephoneService.update(id, dto);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        telephoneService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
