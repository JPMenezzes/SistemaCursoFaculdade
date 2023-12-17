package io.github.jpmenezes.faculdade.modules.celulares.controller;

import io.github.jpmenezes.faculdade.modules.celulares.entities.Celular;
import io.github.jpmenezes.faculdade.modules.celulares.repositories.CelularRepository;
import io.github.jpmenezes.faculdade.modules.celulares.services.CelularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/celulares")
public class CelularController {

    @Autowired
    private CelularRepository repository;

    @GetMapping
    public List<Celular> getAllCelulares() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Celular> getCelularById(@PathVariable Long id) {
        return repository.findById(id)
                .map(celular -> ResponseEntity.ok().body(celular))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Celular> criarCelular(@RequestBody Celular celular) {
        return ResponseEntity.ok(repository.save(celular));
    }

}