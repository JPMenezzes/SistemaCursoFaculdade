package io.github.jpmenezes.faculdade.modules.estudantesMatriculados.repositories;

import io.github.jpmenezes.faculdade.modules.estudantesMatriculados.entities.EstudantesMatriculados;

import java.util.List;

public interface IEstudantesMatriculadosRepository {
    EstudantesMatriculados findById(Long id);
    EstudantesMatriculados save(EstudantesMatriculados estudantesMatriculados);
    List<EstudantesMatriculados> findAll();
}
