package com.trabalho.petshop.repositories;

import com.trabalho.petshop.model.Agendamento;
import com.trabalho.petshop.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
@Repository

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    @Query(value ="select SUM(valor) from agendamento,servico where agendamento.servico_idservico=servico.idservico and servico.nome =:servico",nativeQuery = true)
    public BigDecimal somaservico(String servico);

    @Query(value ="select SUM(valor) from agendamento,pet,cliente,servico where agendamento.servico_idservico=servico.idservico and agendamento.pet_idpet=pet.idpet " +
            "and pet.cliente_idcliente=cliente.idcliente and cliente.nome =:cliente",nativeQuery = true)
    public BigDecimal somacliente(String cliente);

    @Query(value ="select SUM(valor) from agendamento,pet,servico where agendamento.servico_idservico=servico.idservico and agendamento.pet_idpet=pet.idpet " +
            "and pet.nome =:pet",nativeQuery = true)
    public BigDecimal somapet(String pet);

}
