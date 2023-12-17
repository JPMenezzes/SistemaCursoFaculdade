package io.github.jpmenezes.faculdade.modules.estudantesMatriculados.services;

import io.github.jpmenezes.faculdade.modules.estudantes.entities.Estudante;
import io.github.jpmenezes.faculdade.modules.estudantesMatriculados.entities.EstudantesMatriculados;
import io.github.jpmenezes.faculdade.modules.estudantesMatriculados.repositories.IEstudantesMatriculadosRepository;
import io.github.jpmenezes.faculdade.modules.turmaCurso.entities.TurmaCurso;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EstudantesMatriculadosService {

    private IEstudantesMatriculadosRepository repository;

    public EstudantesMatriculadosService(IEstudantesMatriculadosRepository repository) {
        this.repository = repository;
    }

    public EstudantesMatriculados execute(EstudantesMatriculados estudantesMatriculados) {
        validarMatricula(estudantesMatriculados);
        atualizarVagasDisponiveis(estudantesMatriculados.getTurmaCurso());

        // Adicionar o objeto à lista de estudantes matriculados na instância de TurmaCurso
        estudantesMatriculados.getTurmaCurso().getEstudantesMatriculados().add(estudantesMatriculados);

        return repository.save(estudantesMatriculados);
    }

    public EstudantesMatriculados execute(TurmaCurso turmaCurso, Estudante estudante) {
        // Criar uma instância de EstudantesMatriculados e associar turmaCurso e estudante
        EstudantesMatriculados estudantesMatriculados = new EstudantesMatriculados();
        estudantesMatriculados.setTurmaCurso(turmaCurso);
        estudantesMatriculados.setEstudante(estudante);
        estudantesMatriculados.setDataMatricula(LocalDate.now()); // Ajuste conforme necessário

        // Chamar o método existente para realizar as validações
        validarMatricula(estudantesMatriculados);

        // Atualizar as vagas disponíveis
        atualizarVagasDisponiveis(turmaCurso);

        // Salvar a matrícula
        return repository.save(estudantesMatriculados);
    }

    private void validarMatricula(EstudantesMatriculados estudantesMatriculados) {
        LocalDate dataMatricula = estudantesMatriculados.getDataMatricula();
        TurmaCurso turmaCurso = estudantesMatriculados.getTurmaCurso();

        if (!podeMatricular(dataMatricula, turmaCurso)) {
            throw new RuntimeException("Não foi possível realizar a matrícula.");
        }

        if (turmaCurso.getEstudantesMatriculados() != null && turmaCurso.getEstudantesMatriculados().contains(estudantesMatriculados)) {
            throw new RuntimeException("Estudante já matriculado nesta turma.");
        }

        if (!periodoMatriculasAnteriorAoCurso(turmaCurso.getPeriodoMatriculas(), turmaCurso.getInicioAulas())) {
            throw new RuntimeException("O período de matrículas deve ser anterior ao período do curso.");
        }
    }

    private boolean podeMatricular(LocalDate dataMatricula, TurmaCurso turmaCurso) {
        return dataMatricula != null &&
                turmaCurso != null &&
                turmaCurso.getVagasDisponiveis() > 0 &&
                turmaCurso.getPeriodoMatriculas() != null &&
                !turmaCurso.getPeriodoMatriculas().isEmpty() &&
                dataMatricula.isAfter(turmaCurso.getPeriodoMatriculas().get(0)) &&
                dataMatricula.isBefore(turmaCurso.getPeriodoMatriculas().get(1));
    }

    private boolean periodoMatriculasAnteriorAoCurso(List<LocalDate> periodoMatriculas, LocalDate inicioAulas) {
        return periodoMatriculas != null && !periodoMatriculas.isEmpty() &&
                periodoMatriculas.get(1) != null && inicioAulas != null &&
                periodoMatriculas.get(1).isBefore(inicioAulas);
    }

    private void atualizarVagasDisponiveis(TurmaCurso turmaCurso) {
        int vagasDisponiveis = turmaCurso.getVagasDisponiveis();
        if (vagasDisponiveis > 0) {
            turmaCurso.setVagasDisponiveis(vagasDisponiveis - 1);
        } else {
            throw new RuntimeException("Não há vagas disponíveis para matrícula.");
        }
    }
}
