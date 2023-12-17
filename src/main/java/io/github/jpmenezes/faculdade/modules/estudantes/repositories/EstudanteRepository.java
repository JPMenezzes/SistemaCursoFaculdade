package io.github.jpmenezes.faculdade.modules.estudantes.repositories;

import io.github.jpmenezes.faculdade.modules.estudantes.entities.Estudante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EstudanteRepository implements IEstudanteRepository {

    @Autowired
    private EstudanteJPARepository estudanteJPARepository;

    @Override
    public Estudante findById(Long id) {
        return this.estudanteJPARepository.findById(id).orElse(null);
    }

    @Override
    public Estudante findByMatricula(String matricula) {
        return this.estudanteJPARepository.findByMatricula(matricula);
    }

    @Override
    public Estudante save(Estudante estudante) {
        return this.estudanteJPARepository.save(estudante);
    }

    @Override
    public List<Estudante> findAll() {
        return estudanteJPARepository.findAll();
    }
}
