package com.trabalho.petshop.repositories;

import com.trabalho.petshop.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    @Query(value ="select * from funcionario where email =:email and senha =:senha",nativeQuery = true)
    public Funcionario Login(String email,String senha);

}

