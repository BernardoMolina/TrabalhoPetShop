package com.trabalho.petshop.controller;

import com.trabalho.petshop.dto.RequisicaoFormRaca;
import com.trabalho.petshop.model.Raca;
import com.trabalho.petshop.model.Pet;
import com.trabalho.petshop.model.Servico;
import com.trabalho.petshop.model.Tipo;
import com.trabalho.petshop.repositories.RacaRepository;
import com.trabalho.petshop.repositories.PetRepository;
import com.trabalho.petshop.repositories.ServicoRepository;
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
@RequestMapping(value = "/funcionario/raca")
public class RacaController {

    @Autowired
    private RacaRepository racaRepository;
    @Autowired
    private TipoRepository tipoRepository;


    @GetMapping("")
    public ModelAndView index() {
        List<Raca> raca = this.racaRepository.findAll();
        ModelAndView mv = new ModelAndView("funcionario/raca/index");
        mv.addObject("raca", raca);

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(RequisicaoFormRaca requisicao) {
        List<Tipo> tipo = this.tipoRepository.findAll();

        ModelAndView mv = new ModelAndView("funcionario/raca/new");

        mv.addObject("tipo", tipo);


        return mv;
    }


    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoFormRaca requisicao, BindingResult bindingResult) {
        System.out.println(requisicao);
        if (bindingResult.hasErrors()) {
            System.out.println("\n************* TEM ERROS ***************\n");
            List<Tipo> tipo = this.tipoRepository.findAll();

            ModelAndView mv = new ModelAndView("funcionario/raca/new");
            mv.addObject("tipo", tipo);

            return mv;
        } else {
            Raca raca = requisicao.toRaca();
            this.racaRepository.save(raca);

            return new ModelAndView("redirect:/funcionario/raca/" + raca.getIdraca());
        }
    }


    @GetMapping("/{idraca}")
    public ModelAndView show(@PathVariable int idraca) {
        Optional<Raca> optional = this.racaRepository.findById(idraca);

        if (optional.isPresent()) {
            Raca raca = optional.get();

            ModelAndView mv = new ModelAndView("funcionario/raca/show");
            mv.addObject("raca", raca);

            return mv;
        }
        // não achou um registro na tabela Raca com o id informado
        else {
            System.out.println("$$$$$$$$$$$ NÃO ACHOU O RACA DE ID " + idraca + " $$$$$$$$$$$");
            return this.retornaErroRaca("SHOW ERROR: Raca #" + idraca + " não encontrado no banco!");
        }
    }


    @GetMapping("/{idraca}/edit")
    public ModelAndView edit(@PathVariable int idraca, RequisicaoFormRaca requisicao) {
        Optional<Raca> optional = this.racaRepository.findById(idraca);

        if (optional.isPresent()) {
            Raca raca = optional.get();
            requisicao.fromRaca(raca);

            List<Tipo> tipo = this.tipoRepository.findAll();
            ModelAndView mv = new ModelAndView("funcionario/raca/edit");
            mv.addObject("racaId", raca.getIdraca());
            mv.addObject("tipo",tipo);


            return mv;

        }
        // não achou um registro na tabela Raca com o id informado
        else {
            System.out.println("$$$$$$$$$$$ NÃO ACHOU O RACA DE ID " + idraca + " $$$$$$$$$$$");
            return this.retornaErroRaca("EDIT ERROR: Raca #" + idraca + " não encontrado no banco!");
        }
    }


    @PostMapping("/{idraca}")
    public ModelAndView update(@PathVariable int idraca, @Valid RequisicaoFormRaca requisicao, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<Tipo> tipo = this.tipoRepository.findAll();
            ModelAndView mv = new ModelAndView("funcionario/raca/edit");
            mv.addObject("racaId", idraca);
            mv.addObject("tipo",tipo);

            return mv;
        } else {
            Optional<Raca> optional = this.racaRepository.findById(idraca);

            if (optional.isPresent()) {
                Raca raca = requisicao.toRaca(optional.get());
                this.racaRepository.save(raca);

                return new ModelAndView("redirect:/funcionario/raca/" + raca.getIdraca());
            }
            // não achou um registro na tabela Raca com o id informado
            else {
                System.out.println("############ NÃO ACHOU O RACA DE ID " + idraca + " ############");
                return this.retornaErroRaca("UPDATE ERROR: Raca #" + idraca + " não encontrado no banco!");
            }
        }
    }

    @GetMapping("/{idraca}/delete")
    public ModelAndView delete(@PathVariable int idraca) {
        ModelAndView mv = new ModelAndView("redirect:/funcionario/raca");

        try {
            this.racaRepository.deleteById(idraca);
            mv.addObject("mensagem", "Raca #" + idraca + " deletado com sucesso!");
            mv.addObject("erro", false);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            mv = this.retornaErroRaca("DELETE ERROR: Raca #" + idraca + " não encontrado no banco!");
        }

        return mv;
    }

    private ModelAndView retornaErroRaca(String msg) {
        ModelAndView mv = new ModelAndView("redirect:/funcionario/raca");
        mv.addObject("mensagem", msg);
        mv.addObject("erro", true);
        return mv;
    }
}
