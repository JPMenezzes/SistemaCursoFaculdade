package io.github.jpmenezes.faculdade.modules.estudantes.services;

import io.github.jpmenezes.faculdade.modules.estudantes.entities.Estudante;
import io.github.jpmenezes.faculdade.modules.estudantes.repositories.IEstudanteRepository;

import java.time.LocalDate;
import java.time.Period;
public class EstudanteService {
    private final IEstudanteRepository repository;

    public EstudanteService(IEstudanteRepository estudanteRepository) {
        this.repository = estudanteRepository;
    }

    public Estudante execute(Estudante estudante) {
        validarEstudante(estudante);
        semCelular(estudante);
        maiorQue15(estudante);

        // Verificar se a matrícula já existe
        Estudante estudanteExistente = repository.findByMatricula(estudante.getMatricula());
        if (estudanteExistente != null) {
            throw new RuntimeException("Já existe um estudante com essa matrícula.");
        }

        // Se tudo estiver válido, salvar o estudante
        return repository.save(estudante);
    }

    // Adicione outras validações e lógicas conforme necessário

    private void validarEstudante(Estudante estudante) {
        if (estudante.getNome() == null || estudante.getMatricula() == null ||
                estudante.getDataDeNascimento() == null || estudante.getEndereco() == null ||
                estudante.getEmailInstitucional() == null) {
            throw new IllegalArgumentException("Todos os campos do estudante são obrigatórios.");
        }
    }

    private void semCelular(Estudante estudante) {
        if (estudante.getCelulares() == null || estudante.getCelulares().isEmpty()) {
            throw new IllegalArgumentException("O estudante deve ter no mínimo 1 número de celular.");
        }
    }

    private void maiorQue15(Estudante estudante) {
        LocalDate dataNascimento = estudante.getDataDeNascimento();
        LocalDate hoje = LocalDate.now();

        Period periodo = Period.between(dataNascimento, hoje);

        if (periodo.getYears() < 15 || (periodo.getYears() == 15 && periodo.getMonths() == 0 && periodo.getDays() == 0)) {
            throw new IllegalArgumentException("O estudante deve ter no mínimo 15 anos completos.");
        }
    }
}
