package io.github.jpmenezes.faculdade.modules.turmaCurso.repositories;

import io.github.jpmenezes.faculdade.modules.turmaCurso.entities.TurmaCurso;

import java.util.List;

public interface ITurmaCursoRepository {
    TurmaCurso findById(Long id);
    TurmaCurso save(TurmaCurso turmaCurso);
    List<TurmaCurso> findAll();
}
