package io.github.jpmenezes.faculdade.modules.estudantesMatriculados.repositories;

import io.github.jpmenezes.faculdade.modules.estudantesMatriculados.entities.EstudantesMatriculados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstudantesMatriculadosRepository implements IEstudantesMatriculadosRepository {

    @Autowired
    private EstudantesMatriculadosJPARepository jpaRepository;

    @Override
    public EstudantesMatriculados findById(Long id) {
        return this.jpaRepository.findById(id).orElse(null);
    }

    @Override
    public EstudantesMatriculados save(EstudantesMatriculados estudantesMatriculados) {
        return this.jpaRepository.save(estudantesMatriculados);
    }

    @Override
    public List<EstudantesMatriculados> findAll() {
        return this.jpaRepository.findAll();
    }
}
