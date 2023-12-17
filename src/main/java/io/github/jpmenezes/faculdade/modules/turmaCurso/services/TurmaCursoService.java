package io.github.jpmenezes.faculdade.modules.turmaCurso.services;

import io.github.jpmenezes.faculdade.modules.estudantesMatriculados.entities.EstudantesMatriculados;
import io.github.jpmenezes.faculdade.modules.turmaCurso.entities.TurmaCurso;
import io.github.jpmenezes.faculdade.modules.turmaCurso.repositories.ITurmaCursoRepository;

import java.util.ArrayList;
import java.util.List;

public class TurmaCursoService {

    private ITurmaCursoRepository repository;

    public TurmaCursoService(ITurmaCursoRepository repository) {
        this.repository = repository;
    }

    public TurmaCurso execute(TurmaCurso turmaCurso) {
        validarCampos(turmaCurso);
        turmaTemEstudantesMatriculados(turmaCurso.getEstudantesMatriculados());


        return repository.save(turmaCurso);
    }

    private void validarCampos(TurmaCurso turmaCurso) {
        if (turmaCurso == null || turmaCurso.getLocal() == null || turmaCurso.getLocal().isEmpty()
                || turmaCurso.getVagas() <= 0 || turmaCurso.getInicioAulas() == null
                || turmaCurso.getFimAulas() == null || turmaCurso.getInicioMatriculas() == null
                || turmaCurso.getFimMatriculas() == null) {
            throw new IllegalArgumentException("Todos os campos da turma de curso são obrigatórios.");
        }
    }

    //Só é possível listar turmas que tenham estudantes matriculados
    private boolean turmaTemEstudantesMatriculados(List<EstudantesMatriculados> estudantesMatriculados) {
        return estudantesMatriculados != null && !estudantesMatriculados.isEmpty();
    }


    public List<TurmaCurso> listarTurmasComEstudantesMatriculados() {
        List<TurmaCurso> turmasComEstudantes = new ArrayList<>();

        // Obtenha a lista de todas as turmas
        List<TurmaCurso> todasAsTurmas = repository.findAll();

        for (TurmaCurso turmaCurso : todasAsTurmas) {
            // Verifique se a turma tem estudantes matriculados
            if (turmaCurso.getEstudantesMatriculados() != null) {
                turmasComEstudantes.add(turmaCurso);
            }
        }

        return turmasComEstudantes;
    }


}
