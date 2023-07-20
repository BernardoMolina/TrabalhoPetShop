package com.trabalho.petshop.controller;

import com.trabalho.petshop.dto.RequisicaoFormFuncionario;
import com.trabalho.petshop.model.Funcionario;
import com.trabalho.petshop.repositories.FuncionarioRepository;
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
@RequestMapping(value = "/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("")
    public ModelAndView index() {
        List<Funcionario> funcionario = this.funcionarioRepository.findAll();
        ModelAndView mv = new ModelAndView("funcionario/index");
        mv.addObject("funcionario", funcionario);

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(RequisicaoFormFuncionario requisicao) {
        ModelAndView mv = new ModelAndView("funcionario/new");


        return mv;
    }


    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoFormFuncionario requisicao, BindingResult bindingResult) {
        System.out.println(requisicao);
        if (bindingResult.hasErrors()) {
            System.out.println("\n************* TEM ERROS ***************\n");

            ModelAndView mv = new ModelAndView("funcionario/new");

            return mv;
        } else {
            Funcionario funcionario = requisicao.toFuncionario();
            this.funcionarioRepository.save(funcionario);

            return new ModelAndView("redirect:/funcionario/" + funcionario.getIdfuncionario());
        }
    }


    @GetMapping("/{idfuncionario}")
    public ModelAndView show(@PathVariable int idfuncionario) {
        Optional<Funcionario> optional = this.funcionarioRepository.findById(idfuncionario);

        if (optional.isPresent()) {
            Funcionario funcionario = optional.get();

            ModelAndView mv = new ModelAndView("funcionario/show");
            mv.addObject("funcionario", funcionario);

            return mv;
        }
        // não achou um registro na tabela Funcionario com o id informado
        else {
            System.out.println("$$$$$$$$$$$ NÃO ACHOU O Funcionario DE ID " + idfuncionario + " $$$$$$$$$$$");
            return this.retornaErroFuncionario("SHOW ERROR: Funcionario #" + idfuncionario + " não encontrado no banco!");
        }
    }


    @GetMapping("/{idfuncionario}/edit")
    public ModelAndView edit(@PathVariable int idfuncionario, RequisicaoFormFuncionario requisicao) {
        Optional<Funcionario> optional = this.funcionarioRepository.findById(idfuncionario);

        if (optional.isPresent()) {
            Funcionario funcionario = optional.get();
            requisicao.fromFuncionario(funcionario);

            ModelAndView mv = new ModelAndView("funcionario/edit");
            mv.addObject("funcionarioId", funcionario.getIdfuncionario());

            return mv;

        }
        // não achou um registro na tabela Funcionario com o id informado
        else {
            System.out.println("$$$$$$$$$$$ NÃO ACHOU O Funcionario DE ID " + idfuncionario + " $$$$$$$$$$$");
            return this.retornaErroFuncionario("EDIT ERROR: Funcionario #" + idfuncionario + " não encontrado no banco!");
        }
    }


    @PostMapping("/{idfuncionario}")
    public ModelAndView update(@PathVariable int idfuncionario, @Valid RequisicaoFormFuncionario requisicao, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("funcionario/edit");
            mv.addObject("funcionarioId", idfuncionario);

            return mv;
        } else {
            Optional<Funcionario> optional = this.funcionarioRepository.findById(idfuncionario);

            if (optional.isPresent()) {
                Funcionario funcionario = requisicao.toFuncionario(optional.get());
                this.funcionarioRepository.save(funcionario);

                return new ModelAndView("redirect:/funcionario/" + funcionario.getIdfuncionario());
            }
            // não achou um registro na tabela Funcionario com o id informado
            else {
                System.out.println("############ NÃO ACHOU O Funcionario DE ID " + idfuncionario + " ############");
                return this.retornaErroFuncionario("UPDATE ERROR: Funcionario #" + idfuncionario + " não encontrado no banco!");
            }
        }
    }

    @GetMapping("/{idfuncionario}/delete")
    public ModelAndView delete(@PathVariable int idfuncionario) {
        ModelAndView mv = new ModelAndView("redirect:/funcionario");

        try {
            this.funcionarioRepository.deleteById(idfuncionario);
            mv.addObject("mensagem", "Funcionario #" + idfuncionario + " deletado com sucesso!");
            mv.addObject("erro", false);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            mv = this.retornaErroFuncionario("DELETE ERROR: Funcionario #" + idfuncionario + " não encontrado no banco!");
        }

        return mv;
    }

    private ModelAndView retornaErroFuncionario(String msg) {
        ModelAndView mv = new ModelAndView("redirect:/funcionario");
        mv.addObject("mensagem", msg);
        mv.addObject("erro", true);
        return mv;
    }
}

