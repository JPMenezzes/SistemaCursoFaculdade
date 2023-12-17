package io.github.jpmenezes.faculdade.modules.turmaCurso.repositories;

import io.github.jpmenezes.faculdade.modules.turmaCurso.entities.TurmaCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaCursoJPARepository extends JpaRepository<TurmaCurso, Long> {
    // Adicione métodos de consulta personalizados, se necessário
}
