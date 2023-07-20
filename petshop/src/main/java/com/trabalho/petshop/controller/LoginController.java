package com.trabalho.petshop.controller;

import com.trabalho.petshop.model.Funcionario;
import com.trabalho.petshop.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @Autowired
    private FuncionarioRepository repo;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/logar")
    public ModelAndView logar(String email, String senha){
        Funcionario funcionario = this.repo.Login(email,senha);
        if(funcionario != null)
        {
            ModelAndView mv = new ModelAndView("redirect:/funcionario");

            return mv;
        }
        ModelAndView mv = new ModelAndView("redirect:/");
        mv = this.retornaErroLogin("Email e/ou senha inválidos");
        return mv;
    }

    private ModelAndView retornaErroLogin(String msg) {
        ModelAndView mv = new ModelAndView("redirect:/");
        mv.addObject("mensagem", msg);
        mv.addObject("erro", true);
        return mv;
    }
}
