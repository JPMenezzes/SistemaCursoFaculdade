package io.github.jpmenezes.faculdade.modules.estudantes.repositories;

import io.github.jpmenezes.faculdade.modules.estudantes.entities.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EstudanteJPARepository extends JpaRepository<Estudante, Long> {
    Estudante findByMatricula(String matricula);
}