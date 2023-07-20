package com.trabalho.petshop.model;

import jakarta.persistence.*;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpet;
    private String nome;
    private String data_de_nascimento;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Tipo tipo;
    @ManyToOne
    private Raca raca;

    public Pet() {
    }

    public Pet(Integer idpet, String nome, String data_de_nascimento, Cliente cliente, Tipo tipo, Raca raca) {
        this.idpet = idpet;
        this.nome = nome;
        this.data_de_nascimento = data_de_nascimento;
        this.cliente = cliente;
        this.tipo = tipo;
        this.raca = raca;
    }

    public Integer getIdpet() {
        return idpet;
    }

    public void setIdpet(Integer idpet) {
        this.idpet = idpet;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_de_nascimento() {
        return data_de_nascimento;
    }

    public void setData_de_nascimento(String data_de_nascimento) {
        this.data_de_nascimento = data_de_nascimento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "idagendamento=" + idpet +
                ", nome pet='" + nome + '\'' +
                ", nome do cliente=" + cliente +
                ", data=" + data_de_nascimento +
                ", tipo=" + tipo +
                ", raca=" + raca +
                '}';
    }

}
