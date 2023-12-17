package io.github.jpmenezes.faculdade.modules.turmaCurso.repositories;

import io.github.jpmenezes.faculdade.modules.turmaCurso.entities.TurmaCurso;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TurmaCursoEmMemoriaRepository implements ITurmaCursoRepository {
    private static Long lastGeneratedId = 0L;
    private List<TurmaCurso> turmaCursos;

    public TurmaCursoEmMemoriaRepository() {
        this.turmaCursos = new ArrayList<>();
    }

    @Override
    public TurmaCurso findById(Long id) {
        Optional<TurmaCurso> optionalTurmaCurso = turmaCursos.stream().filter(tc -> tc.getId().equals(id)).findFirst();
        return optionalTurmaCurso.orElse(null);
    }

    @Override
    public TurmaCurso save(TurmaCurso turmaCurso) {
        turmaCurso.setId(++lastGeneratedId);
        turmaCursos.add(turmaCurso);
        return turmaCurso;
    }

    @Override
    public List<TurmaCurso> findAll() {
        return new ArrayList<>(turmaCursos);
    }
}

