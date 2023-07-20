package com.trabalho.petshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idagendamento;

    private String dataa;


    private String hora;

    @ManyToOne
    private Servico servico;

    @ManyToOne
    private Pet pet;

    public Agendamento() {
    }

    public Agendamento(Integer idagendamento, String dataa, String hora, Servico servico, Pet pet) {
        this.idagendamento = idagendamento;
        this.dataa = dataa;
        this.hora = hora;
        this.servico = servico;
        this.pet = pet;
    }

    public Integer getIdagendamento() {
        return idagendamento;
    }

    public void setIdagendamento(Integer idagendamento) {
        this.idagendamento = idagendamento;
    }

    public String getDataa() {
        return dataa;
    }

    public void setDataa(String dataa) {
        this.dataa = dataa;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }


    @Override
    public String toString() {
        return "Agendamento{" +
                "idagendamento=" + idagendamento +
                ", nome pet='" + pet + '\'' +
                ", nome do serviço=" + servico +
                ", data=" + dataa +
                ", hora=" + hora +
                '}';
    }
}
