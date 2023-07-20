package com.trabalho.petshop.dto;

import com.trabalho.petshop.model.Agendamento;
import com.trabalho.petshop.model.Pet;
import com.trabalho.petshop.model.Servico;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RequisicaoFormAgendamento {

    @NotBlank
    private String dataa;

    @NotBlank
    private String hora;

    @ManyToOne
    @NotNull
    private Servico servico;

    @ManyToOne
    @NotNull
    private Pet pet;

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

    public Agendamento toAgendamento() {
        Agendamento agendamento = new Agendamento();
        agendamento.setDataa(this.dataa);
        agendamento.setPet(this.pet);
        agendamento.setServico(this.servico);
        agendamento.setHora(this.hora);

        return agendamento;
    }


    public Agendamento toAgendamento(Agendamento agendamento) {
        agendamento.setDataa(this.dataa);
        agendamento.setPet(this.pet);
        agendamento.setServico(this.servico);
        agendamento.setHora(this.hora);

        return agendamento;
    }

    public void fromAgendamento(Agendamento agendamento) {
        this.dataa = agendamento.getDataa();
        this.hora = agendamento.getHora();
        this.pet = agendamento.getPet();
        this.servico = agendamento.getServico();
    }

    @Override
    public String toString() {
        return "RequisicaoNovoAgendamento{" +
                "nome pet='" + pet.getIdpet() + '\'' +
                ",nome do serviço=" + servico.getIdservico() +
                ", data=" + dataa +
                ", hora=" + hora +
                '}';
    }
}
