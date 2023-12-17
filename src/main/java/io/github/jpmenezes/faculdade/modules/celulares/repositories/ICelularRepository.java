package io.github.jpmenezes.faculdade.modules.celulares.repositories;

import io.github.jpmenezes.faculdade.modules.celulares.entities.Celular;

import java.util.List;
import java.util.Optional;

public interface ICelularRepository {
    List<Celular> findAll();
    Optional<Celular> findById(Long id);
    Celular save(Celular celular);

    /*void delete(Celular celular);*/
}

