package io.github.jpmenezes.faculdade.modules.estudantesMatriculados.repositories;

import io.github.jpmenezes.faculdade.modules.estudantesMatriculados.entities.EstudantesMatriculados;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EstudantesMatriculadosEmMemoriaRepository implements IEstudantesMatriculadosRepository {

    private static Long lastGeneratedId = 0L;
    private List<EstudantesMatriculados> estudantesMatriculadosList;

    public EstudantesMatriculadosEmMemoriaRepository() {
        this.estudantesMatriculadosList = new ArrayList<>();
    }

    @Override
    public EstudantesMatriculados findById(Long id) {
        Optional<EstudantesMatriculados> optionalEstudantesMatriculados = estudantesMatriculadosList
                .stream()
                .filter(em -> em.getId().equals(id))
                .findFirst();
        return optionalEstudantesMatriculados.orElse(null);
    }

    @Override
    public EstudantesMatriculados save(EstudantesMatriculados estudantesMatriculados) {
        estudantesMatriculados.setId(++lastGeneratedId);
        estudantesMatriculadosList.add(estudantesMatriculados);
        return estudantesMatriculados;
    }

    @Override
    public List<EstudantesMatriculados> findAll() {
        return new ArrayList<>(estudantesMatriculadosList);
    }
}
