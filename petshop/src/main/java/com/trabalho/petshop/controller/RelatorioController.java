package com.trabalho.petshop.controller;

import com.trabalho.petshop.dto.RequisicaoFormAgendamento;
import com.trabalho.petshop.model.Agendamento;
import com.trabalho.petshop.model.Cliente;
import com.trabalho.petshop.model.Pet;
import com.trabalho.petshop.model.Servico;
import com.trabalho.petshop.repositories.AgendamentoRepository;
import com.trabalho.petshop.repositories.ClienteRepository;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/funcionario/relatorio")
public class RelatorioController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private PetRepository petRepository;



    @GetMapping("")
    public ModelAndView index() {
        List<Pet> pet = this.petRepository.findAll();
        List<Servico> servico = this.servicoRepository.findAll();
        List<Cliente> cliente = this.clienteRepository.findAll();

        ModelAndView mv = new ModelAndView("funcionario/relatorio/index");
        mv.addObject("servico", servico);
        mv.addObject("cliente", cliente);
        mv.addObject("pet",pet);
        mv.addObject("agendamentoRepository",agendamentoRepository);


        return mv;
    }

}
