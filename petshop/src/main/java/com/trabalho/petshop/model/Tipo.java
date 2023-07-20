package com.trabalho.petshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idtipo;

    private String nome;

    public Tipo() {
    }

    public Tipo(Integer idtipo, String nome) {
        this.idtipo = idtipo;
        this.nome = nome;
    }

    public Integer getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(Integer idtipo) {
        this.idtipo = idtipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Tipo{" +
                "idtipo=" + idtipo +
                ", nome ='" + nome + '\'' +
                '}';
    }

}
