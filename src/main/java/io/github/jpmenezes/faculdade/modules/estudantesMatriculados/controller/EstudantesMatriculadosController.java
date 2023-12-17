package io.github.jpmenezes.faculdade.modules.estudantesMatriculados.controller;

import io.github.jpmenezes.faculdade.modules.estudantesMatriculados.entities.EstudantesMatriculados;
import io.github.jpmenezes.faculdade.modules.estudantesMatriculados.repositories.EstudantesMatriculadosRepository;
import io.github.jpmenezes.faculdade.modules.estudantesMatriculados.services.EstudantesMatriculadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudantes-matriculados")
public class EstudantesMatriculadosController {

    @Autowired
    private EstudantesMatriculadosRepository repository;

    @PostMapping("/criar")
    public ResponseEntity<EstudantesMatriculados> criarEstudantesMatriculados(@RequestBody EstudantesMatriculados estudantesMatriculados) {
        try {
            EstudantesMatriculadosService estudantesMatriculadosService = new EstudantesMatriculadosService(repository);
            EstudantesMatriculados result = estudantesMatriculadosService.execute(estudantesMatriculados);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public EstudantesMatriculados buscarCursoPorId(@PathVariable Long id) {
        return repository.findById(id);
    }

    @GetMapping("/listarTodos")
    public List<EstudantesMatriculados> listarTodosEstudantes() {
        return repository.findAll();
    }

    // Outros métodos conforme necessário
}
