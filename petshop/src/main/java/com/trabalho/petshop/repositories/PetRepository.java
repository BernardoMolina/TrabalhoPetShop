package com.trabalho.petshop.repositories;

import com.trabalho.petshop.model.Pet;
import com.trabalho.petshop.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {


}
