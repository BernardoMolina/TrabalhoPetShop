package com.trabalho.petshop.model;

import jakarta.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcliente;

    private String nome;

    private String rg;

    private String cpf;

    private String telefone;

    private String rua;

    private String bairro;

    private String complemento;

    private String cep;

    private String cidade;

    private String estado;

    private String pais;

    public Cliente() {
    }

    public Cliente(Integer idcliente, String nome, String rg, String cpf, String telefone, String rua, String bairro, String complemento, String cep, String cidade, String estado, String pais) {
        this.idcliente = idcliente;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.telefone = telefone;
        this.rua = rua;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idcliente=" + idcliente +
                ", nome='" + nome + '\'' +
                ", rg=" + rg +
                ", cpf=" + cpf +
                ", telefone=" + telefone +
                ", rua=" + rua +
                ", bairro=" + bairro +
                ", complemento=" + complemento +
                ", cep=" + cep +
                ", rua=" + rua +
                ", cidade=" + cidade +
                ", estado=" + estado +
                ", pais=" + pais +
                '}';
    }
}