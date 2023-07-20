package com.trabalho.petshop.controller;


import com.trabalho.petshop.dto.RequisicaoFormCliente;
import com.trabalho.petshop.model.Cliente;
import com.trabalho.petshop.model.Pet;
import com.trabalho.petshop.repositories.ClienteRepository;
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
@RequestMapping(value = "/funcionario/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;


    @GetMapping("")
    public ModelAndView index() {
        List<Cliente> cliente = this.clienteRepository.findAll();
        ModelAndView mv = new ModelAndView("funcionario/cliente/index");
        mv.addObject("cliente", cliente);

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(RequisicaoFormCliente requisicao) {
        ModelAndView mv = new ModelAndView("funcionario/cliente/new");


        return mv;
    }


    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoFormCliente requisicao, BindingResult bindingResult) {
        System.out.println(requisicao);
        if (bindingResult.hasErrors()) {
            System.out.println("\n************* TEM ERROS ***************\n");

            ModelAndView mv = new ModelAndView("funcionario/cliente/new");

            return mv;
        } else {
            Cliente cliente = requisicao.toCliente();
            this.clienteRepository.save(cliente);

            return new ModelAndView("redirect:/funcionario/cliente/" + cliente.getIdcliente());
        }
    }


    @GetMapping("/{idcliente}")
    public ModelAndView show(@PathVariable int idcliente) {
        Optional<Cliente> optional = this.clienteRepository.findById(idcliente);


        if (optional.isPresent()) {
            Cliente cliente = optional.get();

            ModelAndView mv = new ModelAndView("funcionario/cliente/show");
            mv.addObject("cliente", cliente);


            return mv;
        }
        // não achou um registro na tabela Cliente com o id informado
        else {
            System.out.println("$$$$$$$$$$$ NÃO ACHOU O CLIENTE DE ID " + idcliente + " $$$$$$$$$$$");
            return this.retornaErroCliente("SHOW ERROR: Cliente #" + idcliente + " não encontrado no banco!");
        }
    }


    @GetMapping("/{idcliente}/edit")
    public ModelAndView edit(@PathVariable int idcliente, RequisicaoFormCliente requisicao) {
        Optional<Cliente> optional = this.clienteRepository.findById(idcliente);

        if (optional.isPresent()) {
            Cliente cliente = optional.get();
            requisicao.fromCliente(cliente);

            ModelAndView mv = new ModelAndView("funcionario/cliente/edit");
            mv.addObject("clienteId", cliente.getIdcliente());

            return mv;

        }
        // não achou um registro na tabela Cliente com o id informado
        else {
            System.out.println("$$$$$$$$$$$ NÃO ACHOU O CLIENTE DE ID " + idcliente + " $$$$$$$$$$$");
            return this.retornaErroCliente("EDIT ERROR: Cliente #" + idcliente + " não encontrado no banco!");
        }
    }


    @PostMapping("/{idcliente}")
    public ModelAndView update(@PathVariable int idcliente, @Valid RequisicaoFormCliente requisicao, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("funcionario/cliente/edit");
            mv.addObject("clienteId", idcliente);

            return mv;
        } else {
            Optional<Cliente> optional = this.clienteRepository.findById(idcliente);

            if (optional.isPresent()) {
                Cliente cliente = requisicao.toCliente(optional.get());
                this.clienteRepository.save(cliente);

                return new ModelAndView("redirect:/funcionario/cliente/" + cliente.getIdcliente());
            }
            // não achou um registro na tabela Cliente com o id informado
            else {
                System.out.println("############ NÃO ACHOU O CLIENTE DE ID " + idcliente + " ############");
                return this.retornaErroCliente("UPDATE ERROR: Cliente #" + idcliente + " não encontrado no banco!");
            }
        }
    }

    @GetMapping("/{idcliente}/delete")
    public ModelAndView delete(@PathVariable int idcliente) {
        ModelAndView mv = new ModelAndView("redirect:/funcionario/cliente");

        try {
            this.clienteRepository.deleteById(idcliente);
            mv.addObject("mensagem", "Cliente #" + idcliente + " deletado com sucesso!");
            mv.addObject("erro", false);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            mv = this.retornaErroCliente("DELETE ERROR: Cliente #" + idcliente + " não encontrado no banco!");
        }

        return mv;
    }

    private ModelAndView retornaErroCliente(String msg) {
        ModelAndView mv = new ModelAndView("redirect:/funcionario/cliente");
        mv.addObject("mensagem", msg);
        mv.addObject("erro", true);
        return mv;
    }
}
