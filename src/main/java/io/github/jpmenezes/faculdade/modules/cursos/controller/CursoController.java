package io.github.jpmenezes.faculdade.modules.cursos.controller;

import io.github.jpmenezes.faculdade.modules.cursos.entities.Curso;
import io.github.jpmenezes.faculdade.modules.cursos.repositories.CursoRepository;
import io.github.jpmenezes.faculdade.modules.cursos.services.CursoService;
import io.github.jpmenezes.faculdade.modules.estudantes.entities.Estudante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @PostMapping("/criar")
    public Curso criarCurso(@RequestBody Curso curso) {
        CursoService cursoService = new CursoService(repository);
        return cursoService.execute(curso);
    }

    @GetMapping("/{id}")
    public Curso buscarCursoPorId(@PathVariable Long id) {
        return repository.findById(id);
    }

    @GetMapping("/buscarPorNome/{nome}")
    public Curso buscarCursoPorNome(@PathVariable String nome) {
        return repository.findByNome(nome);
    }

    @GetMapping("/listarTodos")
    public List<Curso> listarTodosEstudantes() {
        return repository.findAll();
    }

    /*
    @Autowired
    private CursoService service;

    @GetMapping
    public ResponseEntity<List<Curso>> findAll(){
        List<Curso> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Long id){
        Curso obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }*/
}
