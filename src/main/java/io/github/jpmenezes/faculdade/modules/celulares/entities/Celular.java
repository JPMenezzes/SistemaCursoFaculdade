package io.github.jpmenezes.faculdade.modules.celulares.entities;

import io.github.jpmenezes.faculdade.modules.estudantes.entities.Estudante;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_celular")
public class Celular implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;

    @ManyToOne
    @JoinColumn(name = "estudante_id")
    private Estudante estudante;

    public Celular() {
    }

    public Celular(String numero) {
        this.numero = numero;
    }

    public Celular(Long id, String numero) {
        this.id = id;
        this.numero = numero;
    }

    public Celular(Long id, String numero, Estudante estudante) {
        this.id = id;
        this.numero = numero;
        this.estudante = estudante;
    }

    public Celular(String numero, Estudante estudante) {
        this.numero = numero;
        this.estudante = estudante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }
}
