package io.github.jpmenezes.faculdade.modules.cursos.repositories;

import io.github.jpmenezes.faculdade.modules.cursos.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoJPARepository extends JpaRepository<Curso, Long> {
    Curso findByNome(String nome);
}