package com.trabalho.petshop.controller;

import com.trabalho.petshop.dto.RequisicaoFormAgendamento;
import com.trabalho.petshop.model.Agendamento;
import com.trabalho.petshop.model.Pet;
import com.trabalho.petshop.model.Servico;
import com.trabalho.petshop.repositories.AgendamentoRepository;
import com.trabalho.petshop.repositories.PetRepository;
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
@RequestMapping(value = "/funcionario/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping("")
    public ModelAndView index() {
        List<Agendamento> agendamento = this.agendamentoRepository.findAll();
        ModelAndView mv = new ModelAndView("funcionario/agendamento/index");
        mv.addObject("agendamento", agendamento);

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(RequisicaoFormAgendamento requisicao) {
        List<Servico> servico = this.servicoRepository.findAll();
        List<Pet> pet = this.petRepository.findAll();

        ModelAndView mv = new ModelAndView("funcionario/agendamento/new");

        mv.addObject("pet", pet);
        mv.addObject("servico", servico);

        return mv;
    }


    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoFormAgendamento requisicao, BindingResult bindingResult) {
        System.out.println(requisicao);
        if (bindingResult.hasErrors()) {
            System.out.println("\n************* TEM ERROS ***************\n");

            List<Servico> servico = this.servicoRepository.findAll();
            List<Pet> pet = this.petRepository.findAll();

            ModelAndView mv = new ModelAndView("funcionario/agendamento/new");

            mv.addObject("pet", pet);
            mv.addObject("servico", servico);

            return mv;
        } else {
            Agendamento agendamento = requisicao.toAgendamento();
            this.agendamentoRepository.save(agendamento);

            return new ModelAndView("redirect:/funcionario/agendamento/" + agendamento.getIdagendamento());
        }
    }


    @GetMapping("/{idagendamento}")
    public ModelAndView show(@PathVariable int idagendamento) {
        Optional<Agendamento> optional = this.agendamentoRepository.findById(idagendamento);

        if (optional.isPresent()) {
            Agendamento agendamento = optional.get();

            ModelAndView mv = new ModelAndView("funcionario/agendamento/show");
            mv.addObject("agendamento", agendamento);

            return mv;
        }
        // não achou um registro na tabela Agendamento com o id informado
        else {
            System.out.println("$$$$$$$$$$$ NÃO ACHOU O AGENDAMENTO DE ID " + idagendamento + " $$$$$$$$$$$");
            return this.retornaErroAgendamento("SHOW ERROR: Agendamento #" + idagendamento + " não encontrado no banco!");
        }
    }


    @GetMapping("/{idagendamento}/edit")
    public ModelAndView edit(@PathVariable int idagendamento, RequisicaoFormAgendamento requisicao) {
        Optional<Agendamento> optional = this.agendamentoRepository.findById(idagendamento);

        if (optional.isPresent()) {
            Agendamento agendamento = optional.get();
            requisicao.fromAgendamento(agendamento);

            List<Servico> servico = this.servicoRepository.findAll();
            List<Pet> pet = this.petRepository.findAll();

            ModelAndView mv = new ModelAndView("funcionario/agendamento/edit");
            mv.addObject("agendamentoId", agendamento.getIdagendamento());
            mv.addObject("pet", pet);
            mv.addObject("servico", servico);

            return mv;

        }
        // não achou um registro na tabela Agendamento com o id informado
        else {
            System.out.println("$$$$$$$$$$$ NÃO ACHOU O AGENDAMENTO DE ID " + idagendamento + " $$$$$$$$$$$");
            return this.retornaErroAgendamento("EDIT ERROR: Agendamento #" + idagendamento + " não encontrado no banco!");
        }
    }


    @PostMapping("/{idagendamento}")
    public ModelAndView update(@PathVariable int idagendamento, @Valid RequisicaoFormAgendamento requisicao, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            List<Servico> servico = this.servicoRepository.findAll();
            List<Pet> pet = this.petRepository.findAll();

            ModelAndView mv = new ModelAndView("funcionario/agendamento/edit");
            mv.addObject("agendamentoId", idagendamento);
            mv.addObject("pet", pet);
            mv.addObject("servico", servico);

            return mv;
        } else {
            Optional<Agendamento> optional = this.agendamentoRepository.findById(idagendamento);

            if (optional.isPresent()) {
                Agendamento agendamento = requisicao.toAgendamento(optional.get());
                this.agendamentoRepository.save(agendamento);

                return new ModelAndView("redirect:/funcionario/agendamento/" + agendamento.getIdagendamento());
            }
            // não achou um registro na tabela Agendamento com o id informado
            else {
                System.out.println("############ NÃO ACHOU O AGENDAMENTO DE ID " + idagendamento + " ############");
                return this.retornaErroAgendamento("UPDATE ERROR: Agendamento #" + idagendamento + " não encontrado no banco!");
            }
        }
    }

    @GetMapping("/{idagendamento}/delete")
    public ModelAndView delete(@PathVariable int idagendamento) {
        ModelAndView mv = new ModelAndView("redirect:/funcionario/agendamento");

        try {
            this.agendamentoRepository.deleteById(idagendamento);
            mv.addObject("mensagem", "Agendamento #" + idagendamento + " deletado com sucesso!");
            mv.addObject("erro", false);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            mv = this.retornaErroAgendamento("DELETE ERROR: Agendamento #" + idagendamento + " não encontrado no banco!");
        }

        return mv;
    }

    private ModelAndView retornaErroAgendamento(String msg) {
        ModelAndView mv = new ModelAndView("redirect:/funcionario/agendamento");
        mv.addObject("mensagem", msg);
        mv.addObject("erro", true);
        return mv;
    }
}
