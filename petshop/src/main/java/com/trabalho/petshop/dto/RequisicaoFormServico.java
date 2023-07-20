package com.trabalho.petshop.dto;

import com.trabalho.petshop.model.Funcionario;
import com.trabalho.petshop.model.Servico;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RequisicaoFormServico {
    @NotBlank
    @NotNull
    private String nome;
    @NotBlank
    @NotNull
    private String descricao;
    @NotBlank
    @NotNull
    private String duracao;
    @DecimalMin("0.0")
    @NotNull
    private BigDecimal valor;

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

    public Servico toServico() {
        Servico servico = new Servico();
        servico.setNome(this.nome);
        servico.setDescricao(this.descricao);
        servico.setDuracao(this.duracao);
        servico.setValor(this.valor);

        return servico;
    }


    public Servico toServico(Servico servico) {
        servico.setNome(this.nome);
        servico.setDescricao(this.descricao);
        servico.setDuracao(this.duracao);
        servico.setValor(this.valor);

        return servico;
    }

    public void fromServico(Servico servico) {
        this.nome = servico.getNome();
        this.descricao = servico.getDescricao();
        this.duracao = servico.getDuracao();
        this.valor = servico.getValor();
    }

    @Override
    public String toString() {
        return "RequisicaoNovoServico{" +
                ", nome='" + nome + '\'' +
                ", descricao=" + descricao +
                ", duracao=" + duracao +
                ", valor=" + valor +
                '}';
    }
}
