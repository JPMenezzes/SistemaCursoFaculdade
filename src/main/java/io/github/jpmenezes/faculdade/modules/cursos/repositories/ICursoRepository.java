package io.github.jpmenezes.faculdade.modules.cursos.repositories;

import io.github.jpmenezes.faculdade.modules.cursos.entities.Curso;

import java.util.List;

public interface ICursoRepository {
    Curso findById(Long id);
    Curso findByNome(String nome);
    Curso save(Curso curso);
    List<Curso> findAll();
}
