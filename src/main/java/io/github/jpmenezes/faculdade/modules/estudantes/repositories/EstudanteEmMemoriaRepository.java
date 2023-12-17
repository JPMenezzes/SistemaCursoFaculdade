package io.github.jpmenezes.faculdade.modules.estudantes.repositories;

import io.github.jpmenezes.faculdade.modules.estudantes.entities.Estudante;
import org.yaml.snakeyaml.events.Event;

import java.util.*;

public class EstudanteEmMemoriaRepository implements IEstudanteRepository {

    private static Long lastGeneratedId = 0L;
    private List<Estudante> estudantes;

    public EstudanteEmMemoriaRepository() {
        this.estudantes = new ArrayList<>();
    }

    @Override
    public Estudante findById(Long id) {
        Optional<Estudante> optionalEstudante = estudantes.stream().filter(estudante -> estudante.getId().equals(id)).findFirst();
        return optionalEstudante.orElse(null);
    }

    @Override
    public Estudante findByMatricula(String matricula) {
        Optional<Estudante> optionalEstudante = estudantes.stream().filter(estudante -> estudante.getMatricula().equals(matricula)).findFirst();
        return optionalEstudante.orElse(null);
    }

    @Override
    public Estudante save(Estudante estudante) {
        estudante.setId(++lastGeneratedId);
        estudantes.add(estudante);
        return estudante;
    }

    @Override
    public List<Estudante> findAll() {
        return new ArrayList<>(estudantes);
    }
}

