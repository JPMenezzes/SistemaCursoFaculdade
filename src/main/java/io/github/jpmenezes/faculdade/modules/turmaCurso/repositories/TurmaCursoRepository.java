package io.github.jpmenezes.faculdade.modules.turmaCurso.repositories;

import io.github.jpmenezes.faculdade.modules.turmaCurso.entities.TurmaCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaCursoRepository implements ITurmaCursoRepository {

    @Autowired
    private TurmaCursoJPARepository turmaCursoJPARepository;

    @Override
    public TurmaCurso findById(Long id) {
        return this.turmaCursoJPARepository.findById(id).orElse(null);
    }

    @Override
    public TurmaCurso save(TurmaCurso turmaCurso) {
        return this.turmaCursoJPARepository.save(turmaCurso);
    }

    @Override
    public List<TurmaCurso> findAll() {
        return this.turmaCursoJPARepository.findAll();
    }
}
