package io.github.jpmenezes.faculdade.modules.estudantesMatriculados.repositories;

import io.github.jpmenezes.faculdade.modules.estudantesMatriculados.entities.EstudantesMatriculados;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudantesMatriculadosJPARepository extends JpaRepository<EstudantesMatriculados, Long> {
    // Adicione métodos personalizados se necessário
}
