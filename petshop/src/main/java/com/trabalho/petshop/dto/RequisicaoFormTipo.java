package com.trabalho.petshop.dto;

import com.trabalho.petshop.model.Servico;
import com.trabalho.petshop.model.Tipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RequisicaoFormTipo {
    @NotNull
    @NotBlank
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tipo toTipo() {
        Tipo tipo = new Tipo();
        tipo.setNome(this.nome);

        return tipo;
    }


    public Tipo toTipo(Tipo tipo) {
        tipo.setNome(this.nome);

        return tipo;
    }

    public void fromTipo(Tipo tipo) {
        this.nome = tipo.getNome();
    }

    @Override
    public String toString() {
        return "RequisicaoNovoTipo{" +
                ", nome='" + nome + '\'' +
                '}';
    }
}
