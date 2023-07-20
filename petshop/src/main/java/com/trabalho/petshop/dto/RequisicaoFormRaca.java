package com.trabalho.petshop.dto;

import com.trabalho.petshop.model.Raca;
import com.trabalho.petshop.model.Tipo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RequisicaoFormRaca {
    @NotBlank
    @NotNull
    private String nome;
    @NotNull
    private Tipo tipo;

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

    public Raca toRaca() {
        Raca raca = new Raca();
        raca.setNome(this.nome);
        raca.setTipo(this.tipo);

        return raca;
    }


    public Raca toRaca(Raca raca) {
        raca.setNome(this.nome);
        raca.setTipo(this.tipo);

        return raca;
    }

    public void fromRaca(Raca raca) {
        this.nome = raca.getNome();
        this.tipo = raca.getTipo();
    }

    @Override
    public String toString() {
        return "RequisicaoNovoRaca{" +
                "nome raca='" + nome + '\'' +
                ",nome do tipo=" + tipo.getIdtipo() +
                '}';
    }
}
