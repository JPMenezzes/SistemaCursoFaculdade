package io.github.jpmenezes.faculdade.modules.turmaCurso.controller;

import io.github.jpmenezes.faculdade.modules.turmaCurso.entities.TurmaCurso;
import io.github.jpmenezes.faculdade.modules.turmaCurso.repositories.TurmaCursoRepository;
import io.github.jpmenezes.faculdade.modules.turmaCurso.services.TurmaCursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaCursoController {

    @Autowired
    private TurmaCursoRepository repository;

    @PostMapping("/criar")
    public TurmaCurso criarTurmaCurso(@RequestBody TurmaCurso turmaCurso) {
        TurmaCursoService turmaCursoService = new TurmaCursoService(repository);
        return turmaCursoService.execute(turmaCurso);
    }

    @GetMapping("/{id}")
    public TurmaCurso buscarTurmaCursoPorId(@PathVariable Long id) {
        return repository.findById(id);
    }

    @GetMapping("/listarTodos")
    public List<TurmaCurso> listarTodasTurmasCursos() {
        return repository.findAll();
    }

}
