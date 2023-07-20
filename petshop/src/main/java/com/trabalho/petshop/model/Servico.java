package com.trabalho.petshop.model;

import jakarta.persistence.*;


import java.math.BigDecimal;

@Entity
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idservico;
    private String nome;
    private String descricao;
    private String duracao;
    private BigDecimal valor;


    public Servico() {
    }

    public Servico(Integer idservico, String nome, String descricao, String duracao, BigDecimal valor) {
        this.idservico = idservico;
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.valor = valor;
    }

    public Integer getIdservico() {
        return idservico;
    }

    public void setIdservico(Integer idservico) {
        this.idservico = idservico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "idservico=" + idservico +
                ", nome='" + nome + '\'' +
                ", descricao=" + descricao +
                ", duracao=" + duracao +
                ", valor=" + valor +
                '}';
    }
}