package io.github.jpmenezes.faculdade.modules.estudantes.entities;

import io.github.jpmenezes.faculdade.modules.celulares.entities.Celular;
import io.github.jpmenezes.faculdade.modules.estudantesMatriculados.entities.EstudantesMatriculados;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "tb_estudante")
public class Estudante implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String matricula;
    private LocalDate dataDeNascimento;
    private String endereco;
    private String emailInstitucional;

    @OneToMany
    @JoinColumn(name = "estudante_id")
    private List<Celular> celulares;

    @OneToMany(mappedBy = "estudante")
    private Set<EstudantesMatriculados> estudantesMatriculados;

    // Construtores
    public Estudante() {
        // Construtor padrão necessário para o JPA
    }

    public Estudante(String nome, String matricula, LocalDate dataDeNascimento, String endereco, String emailInstitucional) {
        this.nome = nome;
        this.matricula = matricula;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
        this.emailInstitucional = emailInstitucional;
    }

    // Getters e Setters


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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmailInstitucional() {
        return emailInstitucional;
    }

    public void setEmailInstitucional(String emailInstitucional) {
        this.emailInstitucional = emailInstitucional;
    }

    public List<Celular> getCelulares() {
        return celulares;
    }

    public void setCelulares(List<Celular> celulares) {
        this.celulares = celulares;
    }

    public Set<EstudantesMatriculados> getEstudantesMatriculados() {
        return estudantesMatriculados;
    }

    public void setEstudantesMatriculados(Set<EstudantesMatriculados> estudantesMatriculados) {
        this.estudantesMatriculados = estudantesMatriculados;
    }

    // Método para gerar matrícula
    public String gerarMatricula() {
        String iniciaisNome = nome.substring(0, Math.min(nome.length(), 3)).toUpperCase();
        String timestamp = String.valueOf(System.currentTimeMillis());

        return iniciaisNome + "-" + timestamp;
    }

}