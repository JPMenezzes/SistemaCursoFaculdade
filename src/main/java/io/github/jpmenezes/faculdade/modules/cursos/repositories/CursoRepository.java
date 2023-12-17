package io.github.jpmenezes.faculdade.modules.cursos.repositories;

import io.github.jpmenezes.faculdade.modules.cursos.entities.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoRepository implements ICursoRepository {

    @Autowired
    private CursoJPARepository cursoJPARepository;

    @Override
    public Curso findById(Long id) {
        return cursoJPARepository.findById(id).orElse(null);
    }

    @Override
    public Curso findByNome(String nome) {
        return cursoJPARepository.findByNome(nome);
    }

    @Override
    public Curso save(Curso curso) {
        return cursoJPARepository.save(curso);
    }

    @Override
    public List<Curso> findAll() {
        return cursoJPARepository.findAll();
    }
}
