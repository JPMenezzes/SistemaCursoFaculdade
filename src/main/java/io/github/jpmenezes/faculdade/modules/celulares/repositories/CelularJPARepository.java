package io.github.jpmenezes.faculdade.modules.celulares.repositories;

import io.github.jpmenezes.faculdade.modules.celulares.entities.Celular;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CelularJPARepository extends JpaRepository<Celular, Long> {
    Optional<Celular> findById(Long id);
}

