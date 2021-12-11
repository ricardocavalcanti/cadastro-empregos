package com.project.springboot.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	// Significa que iremos rodar em localhost:8080
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("mensagem", "Sistema de cadastro de empregos");
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }   

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/blog")
    public String blog(){
        return "blog";
    }
    
    @RequestMapping("/contato")
    public String contato(){
        return "contato";
    }
    
    @RequestMapping("/secure")
    public String secure(){
        return "seguranca";
    }

}
