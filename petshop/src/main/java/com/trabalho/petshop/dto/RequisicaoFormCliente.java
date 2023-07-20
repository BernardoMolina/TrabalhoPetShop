package com.trabalho.petshop.dto;

import com.trabalho.petshop.model.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public class RequisicaoFormCliente {

    @NotBlank
    @NotNull
    private String nome;
    @NotBlank
    @NotNull

    private String rg;
    @NotBlank
    @NotNull
    @CPF

    private String cpf;
    @NotBlank
    @NotNull

    private String telefone;
    @NotBlank
    @NotNull

    private String rua;
    @NotBlank
    @NotNull

    private String bairro;
    @NotBlank
    @NotNull

    private String complemento;
    @NotBlank
    @NotNull

    private String cep;
    @NotBlank
    @NotNull

    private String cidade;
    @NotBlank
    @NotNull

    private String estado;
    @NotBlank
    @NotNull

    private String pais;

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

    public Cliente toCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setRg(this.rg);
        cliente.setCpf(this.cpf);
        cliente.setTelefone(this.telefone);
        cliente.setRua(this.rua);
        cliente.setBairro(this.bairro);
        cliente.setComplemento(this.complemento);
        cliente.setCep(this.cep);
        cliente.setEstado(this.estado);
        cliente.setCidade(this.cidade);
        cliente.setPais(this.pais);


        return cliente;
    }


    public Cliente toCliente(Cliente cliente) {
        cliente.setNome(this.nome);
        cliente.setRg(this.rg);
        cliente.setCpf(this.cpf);
        cliente.setTelefone(this.telefone);
        cliente.setRua(this.rua);
        cliente.setBairro(this.bairro);
        cliente.setComplemento(this.complemento);
        cliente.setCep(this.cep);
        cliente.setEstado(this.estado);
        cliente.setCidade(this.cidade);
        cliente.setPais(this.pais);
        return cliente;
    }

    public void fromCliente(Cliente cliente) {
        this.nome = cliente.getNome();
        this.rg = cliente.getRg();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.rua = cliente.getRua();
        this.bairro = cliente.getBairro();
        this.complemento=cliente.getComplemento();
        this.cep=cliente.getCep();
        this.estado=cliente.getEstado();
        this.cidade=cliente.getCidade();
        this.pais=cliente.getPais();
    }

    @Override
    public String toString() {
        return "RequisicaoNovoCliente{" +
                "nome='" + nome + '\'' +
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
