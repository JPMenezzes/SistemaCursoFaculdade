package io.github.jpmenezes.faculdade.modules.celulares.repositories;

import io.github.jpmenezes.faculdade.modules.celulares.entities.Celular;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CelularEmMemoriaRepository implements ICelularRepository {

    private List<Celular> celulares;

    public CelularEmMemoriaRepository() {
        this.celulares = new ArrayList<>();
    }

    @Override
    public List<Celular> findAll() {
        return new ArrayList<>(celulares);
    }

    @Override
    public Optional<Celular> findById(Long id) {
        return celulares.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    @Override
    public Celular save(Celular celular) {
        celulares.add(celular);
        celular.setId((long) celulares.size()); // Simulação de ID autoincrementável
        return celular;
    }

}
