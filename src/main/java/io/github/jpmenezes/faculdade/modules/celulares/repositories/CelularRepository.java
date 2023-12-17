package io.github.jpmenezes.faculdade.modules.celulares.repositories;

import io.github.jpmenezes.faculdade.modules.celulares.entities.Celular;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CelularRepository implements ICelularRepository {

    @Autowired
    private CelularJPARepository celularJPARepository;

    @Override
    public Optional<Celular> findById(Long id) {
        return this.celularJPARepository.findById(id);
    }

    @Override
    public Celular save(Celular celular) {
        return this.celularJPARepository.save(celular);
    }

    /*@Override
    public void deleteById(Long id) {
        this.celularJPARepository.deleteById(id);
    }*/

    @Override
    public List<Celular> findAll() {
        return this.celularJPARepository.findAll();
    }
}
