package io.github.jpmenezes.faculdade.modules.cursos.entities;

import io.github.jpmenezes.faculdade.modules.turmaCurso.entities.TurmaCurso;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_curso")
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cargaHoraria;
    private String descricao;

    @OneToMany(mappedBy = "curso"/*, cascade = CascadeType.ALL*/)
    private List<TurmaCurso> turmas;

    public Curso() {
    }

    public Curso(String nome, String cargaHoraria, String descricao) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<TurmaCurso> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<TurmaCurso> turmas) {
        this.turmas = turmas;
    }
}
