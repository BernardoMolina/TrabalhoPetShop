package com.trabalho.petshop.dto;


import com.trabalho.petshop.model.Funcionario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;


public class RequisicaoFormFuncionario {

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
    @Email
    @NotNull
    private String email;
    @NotBlank
    @NotNull
    private String senha;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Funcionario toFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(this.nome);
        funcionario.setRg(this.rg);
        funcionario.setCpf(this.cpf);
        funcionario.setTelefone(this.telefone);
        funcionario.setEmail(this.email);

        funcionario.setSenha(this.senha);


        return funcionario;
    }


    public Funcionario toFuncionario(Funcionario funcionario) {
        funcionario.setNome(this.nome);
        funcionario.setRg(this.rg);
        funcionario.setCpf(this.cpf);
        funcionario.setTelefone(this.telefone);
        funcionario.setEmail(this.email);

        funcionario.setSenha(this.senha);
        return funcionario;
    }

    public void fromFuncionario(Funcionario funcionario) {
        this.nome = funcionario.getNome();
        this.rg = funcionario.getRg();
        this.cpf = funcionario.getCpf();
        this.telefone = funcionario.getTelefone();
        this.email = funcionario.getEmail();
        this.senha = funcionario.getSenha();
    }

    @Override
    public String toString() {
        return "RequisicaoNovoFuncionario{" +
                "nome='" + nome + '\'' +
                ", rg=" + rg +
                ", cpf=" + cpf +
                ", telefone=" + telefone +
                ", email=" + email +
                ", senha=" + senha +
                '}';
    }
}

