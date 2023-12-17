package io.github.jpmenezes.faculdade.modules.estudantes.repositories;

import io.github.jpmenezes.faculdade.modules.estudantes.entities.Estudante;

import java.util.List;
import java.util.UUID;

public interface IEstudanteRepository {
    Estudante findById(Long id);
    Estudante findByMatricula(String matricula);
    Estudante save(Estudante estudante);
    List<Estudante> findAll();
}
