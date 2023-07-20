package com.trabalho.petshop.model;

import jakarta.persistence.*;

@Entity
public class Raca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idraca;

    private String nome;
    @ManyToOne
    private Tipo tipo;

    public Raca() {
    }

    public Raca(Integer idraca, String nome, Tipo tipo) {
        this.idraca = idraca;
        this.nome = nome;
        this.tipo = tipo;
    }

    public Integer getIdraca() {
        return idraca;
    }

    public void setIdraca(Integer idraca) {
        this.idraca = idraca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Raca{" +
                "idraca=" + idraca +
                ", nome='" + nome + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
