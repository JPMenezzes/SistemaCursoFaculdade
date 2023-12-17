package io.github.jpmenezes.faculdade.modules.turmaCurso.entities;

import io.github.jpmenezes.faculdade.modules.cursos.entities.Curso;
import io.github.jpmenezes.faculdade.modules.estudantesMatriculados.entities.EstudantesMatriculados;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_turma_curso")
public class TurmaCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String local;
    private int vagas;
    private LocalDate inicioAulas;
    private LocalDate fimAulas;
    private LocalDate inicioMatriculas;
    private LocalDate fimMatriculas;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "turmaCurso"/*, cascade = CascadeType.ALL*/)
    private List<EstudantesMatriculados> estudantesMatriculados;

    public TurmaCurso() {
        // Construtor padrão
    }

    public TurmaCurso(String local, int vagas, LocalDate inicioAulas, LocalDate fimAulas, LocalDate inicioMatriculas, LocalDate fimMatriculas, Curso curso) {
        this.local = local;
        this.vagas = vagas;
        this.inicioAulas = inicioAulas;
        this.fimAulas = fimAulas;
        this.inicioMatriculas = inicioMatriculas;
        this.fimMatriculas = fimMatriculas;
        this.curso = curso;
    }

    public TurmaCurso(List<EstudantesMatriculados> estudantesMatriculados) {
        this.estudantesMatriculados = estudantesMatriculados;
    }

    public TurmaCurso(Long id, String local, int vagas, LocalDate inicioAulas, LocalDate fimAulas, LocalDate inicioMatriculas, LocalDate fimMatriculas, Curso curso, List<EstudantesMatriculados> estudantesMatriculados) {
        this.id = id;
        this.local = local;
        this.vagas = vagas;
        this.inicioAulas = inicioAulas;
        this.fimAulas = fimAulas;
        this.inicioMatriculas = inicioMatriculas;
        this.fimMatriculas = fimMatriculas;
        this.curso = curso;
        this.estudantesMatriculados = new ArrayList<>(estudantesMatriculados);
    }

    public int getVagasDisponiveis() {
        return this.vagas;
    }

    public void setVagasDisponiveis(int vagas) {
        this.vagas = vagas;
    }

    public List<LocalDate> getPeriodoMatriculas() {
        return List.of(this.inicioMatriculas, this.fimMatriculas);
    }

    //Modelagem

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public LocalDate getInicioAulas() {
        return inicioAulas;
    }

    public void setInicioAulas(LocalDate inicioAulas) {
        this.inicioAulas = inicioAulas;
    }

    public LocalDate getFimAulas() {
        return fimAulas;
    }

    public void setFimAulas(LocalDate fimAulas) {
        this.fimAulas = fimAulas;
    }

    public LocalDate getInicioMatriculas() {
        return inicioMatriculas;
    }

    public void setInicioMatriculas(LocalDate inicioMatriculas) {
        this.inicioMatriculas = inicioMatriculas;
    }

    public LocalDate getFimMatriculas() {
        return fimMatriculas;
    }

    public void setFimMatriculas(LocalDate fimMatriculas) {
        this.fimMatriculas = fimMatriculas;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<EstudantesMatriculados> getEstudantesMatriculados() {
        return estudantesMatriculados;
    }

    public void setEstudantesMatriculados(List<EstudantesMatriculados> estudantesMatriculados) {
        this.estudantesMatriculados = estudantesMatriculados;
    }
}
