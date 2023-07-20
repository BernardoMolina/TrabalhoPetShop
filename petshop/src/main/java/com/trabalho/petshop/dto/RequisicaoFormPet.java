package com.trabalho.petshop.dto;

import com.trabalho.petshop.model.Pet;
import com.trabalho.petshop.model.Cliente;
import com.trabalho.petshop.model.Raca;
import com.trabalho.petshop.model.Tipo;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RequisicaoFormPet {
    @NotBlank
    @NotNull
    private String nome;
    @NotBlank
    @NotNull
    private String data_de_nascimento;
    @NotNull
    private Cliente cliente;
    @NotNull
    private Tipo tipo;
    @NotNull
    private Raca raca;

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

    public Pet toPet() {
        Pet pet = new Pet();
        pet.setNome(this.nome);
        pet.setData_de_nascimento(this.data_de_nascimento);
        pet.setCliente(this.cliente);
        pet.setTipo(this.tipo);
        pet.setRaca(this.raca);

        return pet;
    }


    public Pet toPet(Pet pet) {
        pet.setNome(this.nome);
        pet.setData_de_nascimento(this.data_de_nascimento);
        pet.setCliente(this.cliente);
        pet.setTipo(this.tipo);
        pet.setRaca(this.raca);

        return pet;
    }

    public void fromPet(Pet pet) {
        this.nome = pet.getNome();
        this.data_de_nascimento = pet.getData_de_nascimento();
        this.cliente = pet.getCliente();
        this.tipo = pet.getTipo();
        this.raca = pet.getRaca();
    }

    @Override
    public String toString() {
        return "RequisicaoNovoPet{" +
                "nome pet='" + nome + '\'' +
                ",data de nascimento=" + data_de_nascimento +
                ", cliente=" + cliente.getIdcliente() +
                ", tipo=" + tipo.getIdtipo() +
                ", raca=" + raca.getIdraca() +
                '}';
    }
}
