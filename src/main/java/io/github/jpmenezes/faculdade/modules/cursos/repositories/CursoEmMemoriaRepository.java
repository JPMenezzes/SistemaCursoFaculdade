package io.github.jpmenezes.faculdade.modules.cursos.repositories;

import io.github.jpmenezes.faculdade.modules.cursos.entities.Curso;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CursoEmMemoriaRepository implements ICursoRepository {

    private static Long lastGeneratedId = 0L;
    private List<Curso> cursos;

    public CursoEmMemoriaRepository() {
        this.cursos = new ArrayList<>();
    }

    @Override
    public Curso findById(Long id) {
        Optional<Curso> optionalCurso = cursos.stream().filter(curso -> curso.getId().equals(id)).findFirst();
        return optionalCurso.orElse(null);
    }

    @Override
    public Curso findByNome(String nome) {
        Optional<Curso> optionalCurso = this.cursos.stream().filter(curso -> curso.getNome().equals(nome)).findFirst();
        return optionalCurso.orElse(null);
    }

    @Override
    public Curso save(Curso curso) {
        curso.setId(++lastGeneratedId);
        cursos.add(curso);
        return curso;
    }

    @Override
    public List<Curso> findAll() {
        return new ArrayList<>(cursos);
    }
}
