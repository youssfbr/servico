package com.github.youssfbr.servicos.rest;

import com.github.youssfbr.servicos.model.entities.Client;
import com.github.youssfbr.servicos.services.IClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final IClientService clientService;

    public ClientController(final IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Client> persist(@RequestBody Client client) {

        client = clientService.persist(client);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).body(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client updatedClient) {

        updatedClient = clientService.update(id, updatedClient);

        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        clientService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
