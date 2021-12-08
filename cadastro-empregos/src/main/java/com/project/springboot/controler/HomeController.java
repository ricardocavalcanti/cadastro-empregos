package com.project.springboot.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    // Significa que iremos rodar em localhost:8080
    @RequestMapping("/")
    public String homeApp(Model model){
        model.addAttribute("mensagem", "Sistema de cadastro de empregos");
        return "index";
    }


}
