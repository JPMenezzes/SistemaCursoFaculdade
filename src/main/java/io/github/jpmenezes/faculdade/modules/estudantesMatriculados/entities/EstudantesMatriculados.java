package io.github.jpmenezes.faculdade.modules.estudantesMatriculados.entities;

import io.github.jpmenezes.faculdade.modules.estudantes.entities.Estudante;
import io.github.jpmenezes.faculdade.modules.turmaCurso.entities.TurmaCurso;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_estudantes_matriculados")
public class EstudantesMatriculados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "turma_curso_id")
    private TurmaCurso turmaCurso;

    @ManyToOne
    @JoinColumn(name = "estudante_id")
    private Estudante estudante;

    private LocalDate dataMatricula;

    public EstudantesMatriculados() {
        // Construtor padrão
    }

    public EstudantesMatriculados(TurmaCurso turmaCurso, Estudante estudante, LocalDate dataMatricula) {
        this.turmaCurso = turmaCurso;
        this.estudante = estudante;
        this.dataMatricula = dataMatricula;
    }

    // Getters e setters

    public boolean podeMatricular(LocalDate dataMatricula) {
        List<LocalDate> periodoMatriculas = this.turmaCurso.getPeriodoMatriculas();

        return this.turmaCurso.getVagasDisponiveis() > 0 &&
                dataMatricula.isAfter(periodoMatriculas.get(0)) &&
                dataMatricula.isBefore(periodoMatriculas.get(1));
    }

    // Este método retorna a lista de estudantes matriculados na mesma turma
    public List<EstudantesMatriculados> getMatriculados() {
        TurmaCurso turma = this.turmaCurso;
        List<EstudantesMatriculados> matriculados = turma.getEstudantesMatriculados();

        return matriculados;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TurmaCurso getTurmaCurso() {
        return turmaCurso;
    }

    public void setTurmaCurso(TurmaCurso turmaCurso) {
        this.turmaCurso = turmaCurso;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }
}
