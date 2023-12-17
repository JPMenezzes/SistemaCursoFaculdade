package io.github.jpmenezes.faculdade.modules.estudantes.controller;

import io.github.jpmenezes.faculdade.modules.estudantes.entities.Estudante;
import io.github.jpmenezes.faculdade.modules.estudantes.repositories.EstudanteRepository;
import io.github.jpmenezes.faculdade.modules.estudantes.services.EstudanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {

    @Autowired
    private EstudanteRepository repository;

    @PostMapping("/criar")
    public Estudante criarEstudante(@RequestBody Estudante estudante) {
        EstudanteService estudanteService = new EstudanteService(repository);
        return estudanteService.execute(estudante);
    }

    @GetMapping("/{id}")
    public Estudante buscarEstudantePorId(@PathVariable Long id) {
        return repository.findById(id);
    }

    @GetMapping("/buscarPorMatricula/{matricula}")
    public Estudante buscarEstudantePorMatricula(@PathVariable String matricula) {
        return repository.findByMatricula(matricula);
    }

    @GetMapping("/listarTodos")
    public List<Estudante> listarTodosEstudantes() {
        return repository.findAll();
    }
}
