package com.trabalho.petshop.controller;

import com.trabalho.petshop.dto.RequisicaoFormTipo;
import com.trabalho.petshop.model.Tipo;
import com.trabalho.petshop.repositories.TipoRepository;
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
@RequestMapping(value = "/funcionario/tipo")
public class TipoController {

    @Autowired
    private TipoRepository tipoRepository;

    @GetMapping("")
    public ModelAndView index() {
        List<Tipo> tipo = this.tipoRepository.findAll();
        ModelAndView mv = new ModelAndView("funcionario/tipo/index");
        mv.addObject("tipo", tipo);

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(RequisicaoFormTipo requisicao) {
        ModelAndView mv = new ModelAndView("funcionario/tipo/new");


        return mv;
    }


    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoFormTipo requisicao, BindingResult bindingResult) {
        System.out.println(requisicao);
        if (bindingResult.hasErrors()) {
            System.out.println("\n************* TEM ERROS ***************\n");

            ModelAndView mv = new ModelAndView("funcionario/tipo/new");

            return mv;
        } else {
            Tipo tipo = requisicao.toTipo();
            this.tipoRepository.save(tipo);

            return new ModelAndView("redirect:/funcionario/tipo/" + tipo.getIdtipo());
        }
    }


    @GetMapping("/{idtipo}")
    public ModelAndView show(@PathVariable int idtipo) {
        Optional<Tipo> optional = this.tipoRepository.findById(idtipo);

        if (optional.isPresent()) {
            Tipo tipo = optional.get();

            ModelAndView mv = new ModelAndView("funcionario/tipo/show");
            mv.addObject("tipo", tipo);

            return mv;
        }
        // não achou um registro na tabela Tipo com o id informado
        else {
            System.out.println("$$$$$$$$$$$ NÃO ACHOU O Tipo DE ID " + idtipo + " $$$$$$$$$$$");
            return this.retornaErroTipo("SHOW ERROR: Tipo #" + idtipo + " não encontrado no banco!");
        }
    }


    @GetMapping("/{idtipo}/edit")
    public ModelAndView edit(@PathVariable int idtipo, RequisicaoFormTipo requisicao) {
        Optional<Tipo> optional = this.tipoRepository.findById(idtipo);

        if (optional.isPresent()) {
            Tipo tipo = optional.get();
            requisicao.fromTipo(tipo);

            ModelAndView mv = new ModelAndView("funcionario/tipo/edit");
            mv.addObject("tipoId", tipo.getIdtipo());

            return mv;

        }
        // não achou um registro na tabela Tipo com o id informado
        else {
            System.out.println("$$$$$$$$$$$ NÃO ACHOU O Tipo DE ID " + idtipo + " $$$$$$$$$$$");
            return this.retornaErroTipo("EDIT ERROR: Tipo #" + idtipo + " não encontrado no banco!");
        }
    }


    @PostMapping("/{idtipo}")
    public ModelAndView update(@PathVariable int idtipo, @Valid RequisicaoFormTipo requisicao, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("funcionario/tipo/edit");
            mv.addObject("tipoId", idtipo);

            return mv;
        } else {
            Optional<Tipo> optional = this.tipoRepository.findById(idtipo);

            if (optional.isPresent()) {
                Tipo tipo = requisicao.toTipo(optional.get());
                this.tipoRepository.save(tipo);

                return new ModelAndView("redirect:/funcionario/tipo/" + tipo.getIdtipo());
            }
            // não achou um registro na tabela Tipo com o id informado
            else {
                System.out.println("############ NÃO ACHOU O Tipo DE ID " + idtipo + " ############");
                return this.retornaErroTipo("UPDATE ERROR: Tipo #" + idtipo + " não encontrado no banco!");
            }
        }
    }

    @GetMapping("/{idtipo}/delete")
    public ModelAndView delete(@PathVariable int idtipo) {
        ModelAndView mv = new ModelAndView("redirect:/funcionario/tipo");

        try {
            this.tipoRepository.deleteById(idtipo);
            mv.addObject("mensagem", "Tipo #" + idtipo + " deletado com sucesso!");
            mv.addObject("erro", false);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            mv = this.retornaErroTipo("DELETE ERROR: Tipo #" + idtipo + " não encontrado no banco!");
        }

        return mv;
    }

    private ModelAndView retornaErroTipo(String msg) {
        ModelAndView mv = new ModelAndView("redirect:/funcionario/tipo");
        mv.addObject("mensagem", msg);
        mv.addObject("erro", true);
        return mv;
    }
}
