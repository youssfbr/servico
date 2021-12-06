package com.github.youssfbr.servicos.rest;

import com.github.youssfbr.servicos.dto.BrandDTO;

import com.github.youssfbr.servicos.services.IBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final IBrandService brandService;

    @GetMapping
    public ResponseEntity<List<BrandDTO>> findAll() {
        return ResponseEntity.ok(brandService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BrandDTO> persist(@RequestBody BrandDTO dto) {

        dto = brandService.persist(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDTO> update(@PathVariable Long id, @RequestBody BrandDTO dto) {

        dto = brandService.update(id, dto);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        brandService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
