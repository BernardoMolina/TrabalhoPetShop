package com.trabalho.petshop.controller;

import com.trabalho.petshop.dto.RequisicaoFormPet;
import com.trabalho.petshop.model.*;
import com.trabalho.petshop.model.Pet;
import com.trabalho.petshop.repositories.*;
import com.trabalho.petshop.repositories.PetRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/funcionario/pet")
public class PetController {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private TipoRepository tipoRepository;
    @Autowired
    private RacaRepository racaRepository;
    @Autowired
    private ClienteRepository clienteRepository;


    @GetMapping("")
    public ModelAndView index() {
        List<Pet> pet = this.petRepository.findAll();
        ModelAndView mv = new ModelAndView("funcionario/pet/index");
        mv.addObject("pet", pet);

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(RequisicaoFormPet requisicao) {
        List<Cliente> cliente = this.clienteRepository.findAll();
        List<Tipo> tipo = this.tipoRepository.findAll();
        List<Raca> raca = this.racaRepository.findAll();

        ModelAndView mv = new ModelAndView("funcionario/pet/new");

        mv.addObject("cliente", cliente);
        mv.addObject("tipo", tipo);
        mv.addObject("raca",raca);

        return mv;
    }


    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoFormPet requisicao, BindingResult bindingResult) {
        System.out.println(requisicao);
        if (bindingResult.hasErrors()) {
            System.out.println("\n************* TEM ERROS ***************\n");
            List<Tipo> tipo = this.tipoRepository.findAll();
            List<Cliente> cliente = this.clienteRepository.findAll();
            List<Raca> raca = this.racaRepository.findAll();

            ModelAndView mv = new ModelAndView("funcionario/pet/new");

            mv.addObject("tipo", tipo);
            mv.addObject("cliente", cliente);
            mv.addObject("raca", raca);

            return mv;
        } else {
            Pet pet = requisicao.toPet();
            this.petRepository.save(pet);

            return new ModelAndView("redirect:/funcionario/pet/" + pet.getIdpet());
        }
    }


    @GetMapping("/{idpet}")
    public ModelAndView show(@PathVariable int idpet) {
        Optional<Pet> optional = this.petRepository.findById(idpet);

        if (optional.isPresent()) {
            Pet pet = optional.get();

            ModelAndView mv = new ModelAndView("funcionario/pet/show");
            mv.addObject("pet", pet);

            return mv;
        }
        // não achou um registro na tabela Pet com o id informado
        else {
            System.out.println("$$$$$$$$$$$ NÃO ACHOU O PET DE ID " + idpet + " $$$$$$$$$$$");
            return this.retornaErroPet("SHOW ERROR: Pet #" + idpet + " não encontrado no banco!");
        }
    }


    @GetMapping("/{idpet}/edit")
    public ModelAndView edit(@PathVariable int idpet, RequisicaoFormPet requisicao) {
        Optional<Pet> optional = this.petRepository.findById(idpet);

        if (optional.isPresent()) {
            Pet pet = optional.get();
            requisicao.fromPet(pet);

            List<Cliente> cliente = this.clienteRepository.findAll();
            List<Tipo> tipo = this.tipoRepository.findAll();
            List<Raca> raca = this.racaRepository.findAll();

            ModelAndView mv = new ModelAndView("funcionario/pet/edit");
            mv.addObject("petId", pet.getIdpet());
            mv.addObject("cliente", cliente);
            mv.addObject("tipo", tipo);
            mv.addObject("raca", raca);

            return mv;

        }
        // não achou um registro na tabela Pet com o id informado
        else {
            System.out.println("$$$$$$$$$$$ NÃO ACHOU O PET DE ID " + idpet + " $$$$$$$$$$$");
            return this.retornaErroPet("EDIT ERROR: Pet #" + idpet + " não encontrado no banco!");
        }
    }


    @PostMapping("/{idpet}")
    public ModelAndView update(@PathVariable int idpet, @Valid RequisicaoFormPet requisicao, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<Cliente> cliente = this.clienteRepository.findAll();
            List<Tipo> tipo = this.tipoRepository.findAll();
            List<Raca> raca = this.racaRepository.findAll();

            ModelAndView mv = new ModelAndView("funcionario/pet/edit");
            mv.addObject("petId", idpet);
            mv.addObject("cliente", cliente);
            mv.addObject("tipo", tipo);
            mv.addObject("raca", raca);

            return mv;
        } else {
            Optional<Pet> optional = this.petRepository.findById(idpet);

            if (optional.isPresent()) {
                Pet pet = requisicao.toPet(optional.get());
                this.petRepository.save(pet);

                return new ModelAndView("redirect:/funcionario/pet/" + pet.getIdpet());
            }
            // não achou um registro na tabela Pet com o id informado
            else {
                System.out.println("############ NÃO ACHOU O PET DE ID " + idpet + " ############");
                return this.retornaErroPet("UPDATE ERROR: Pet #" + idpet + " não encontrado no banco!");
            }
        }
    }

    @GetMapping("/{idpet}/delete")
    public ModelAndView delete(@PathVariable int idpet) {
        ModelAndView mv = new ModelAndView("redirect:/funcionario/pet");

        try {
            this.petRepository.deleteById(idpet);
            mv.addObject("mensagem", "Pet #" + idpet + " deletado com sucesso!");
            mv.addObject("erro", false);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            mv = this.retornaErroPet("DELETE ERROR: Pet #" + idpet + " não encontrado no banco!");
        }

        return mv;
    }

    private ModelAndView retornaErroPet(String msg) {
        ModelAndView mv = new ModelAndView("redirect:/funcionario/pet");
        mv.addObject("mensagem", msg);
        mv.addObject("erro", true);
        return mv;
    }
}
