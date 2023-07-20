package com.trabalho.petshop.controller;

import com.trabalho.petshop.dto.RequisicaoFormServico;
import com.trabalho.petshop.model.Servico;
import com.trabalho.petshop.repositories.ServicoRepository;
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
@RequestMapping(value = "/funcionario/servico")
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping("")
    public ModelAndView index() {
        List<Servico> servico = this.servicoRepository.findAll();
        ModelAndView mv = new ModelAndView("funcionario/servico/index");
        mv.addObject("servico", servico);

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(RequisicaoFormServico requisicao) {
        ModelAndView mv = new ModelAndView("funcionario/servico/new");


        return mv;
    }


    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoFormServico requisicao, BindingResult bindingResult) {
        System.out.println(requisicao);
        if (bindingResult.hasErrors()) {
            System.out.println("\n************* TEM ERROS ***************\n");

            ModelAndView mv = new ModelAndView("funcionario/servico/new");

            return mv;
        } else {
            Servico servico = requisicao.toServico();
            this.servicoRepository.save(servico);

            return new ModelAndView("redirect:/funcionario/servico/" + servico.getIdservico());
        }
    }


    @GetMapping("/{idservico}")
    public ModelAndView show(@PathVariable int idservico) {
        Optional<Servico> optional = this.servicoRepository.findById(idservico);

        if (optional.isPresent()) {
            Servico servico = optional.get();

            ModelAndView mv = new ModelAndView("funcionario/servico/show");
            mv.addObject("servico", servico);

            return mv;
        }
        // não achou um registro na tabela Servico com o id informado
        else {
            System.out.println("$$$$$$$$$$$ NÃO ACHOU O Servico DE ID " + idservico + " $$$$$$$$$$$");
            return this.retornaErroServico("SHOW ERROR: Servico #" + idservico + " não encontrado no banco!");
        }
    }


    @GetMapping("/{idservico}/edit")
    public ModelAndView edit(@PathVariable int idservico, RequisicaoFormServico requisicao) {
        Optional<Servico> optional = this.servicoRepository.findById(idservico);

        if (optional.isPresent()) {
            Servico servico = optional.get();
            requisicao.fromServico(servico);

            ModelAndView mv = new ModelAndView("funcionario/servico/edit");
            mv.addObject("servicoId", servico.getIdservico());

            return mv;

        }
        // não achou um registro na tabela Servico com o id informado
        else {
            System.out.println("$$$$$$$$$$$ NÃO ACHOU O Servico DE ID " + idservico + " $$$$$$$$$$$");
            return this.retornaErroServico("EDIT ERROR: Servico #" + idservico + " não encontrado no banco!");
        }
    }


    @PostMapping("/{idservico}")
    public ModelAndView update(@PathVariable int idservico, @Valid RequisicaoFormServico requisicao, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("funcionario/servico/edit");
            mv.addObject("servicoId", idservico);

            return mv;
        } else {
            Optional<Servico> optional = this.servicoRepository.findById(idservico);

            if (optional.isPresent()) {
                Servico servico = requisicao.toServico(optional.get());
                this.servicoRepository.save(servico);

                return new ModelAndView("redirect:/funcionario/servico/" + servico.getIdservico());
            }
            // não achou um registro na tabela Servico com o id informado
            else {
                System.out.println("############ NÃO ACHOU O Servico DE ID " + idservico + " ############");
                return this.retornaErroServico("UPDATE ERROR: Servico #" + idservico + " não encontrado no banco!");
            }
        }
    }

    @GetMapping("/{idservico}/delete")
    public ModelAndView delete(@PathVariable int idservico) {
        ModelAndView mv = new ModelAndView("redirect:/funcionario/servico");

        try {
            this.servicoRepository.deleteById(idservico);
            mv.addObject("mensagem", "Servico #" + idservico + " deletado com sucesso!");
            mv.addObject("erro", false);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            mv = this.retornaErroServico("DELETE ERROR: Servico #" + idservico + " não encontrado no banco!");
        }

        return mv;
    }

    private ModelAndView retornaErroServico(String msg) {
        ModelAndView mv = new ModelAndView("redirect:/funcionario/servico");
        mv.addObject("mensagem", msg);
        mv.addObject("erro", true);
        return mv;
    }
}
