package com.project.springboot.controler;

import com.project.springboot.model.Emprego;
import com.project.springboot.repository.EmpregoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmpregoController {

    @Autowired
    EmpregoRepository  empregoRepository;

    @RequestMapping("/listarEmprego")
    public String listarEmpregos(Model model){
       model.addAttribute("empregos", empregoRepository.findAll());
       return "listarEmpregos";
    }

    @RequestMapping("/cadastrarEmpregos")
    public String cadastrarEmpregos(Model model){
        model.addAttribute("emprego", new Emprego());
        return "cadastrarEmpregos";
    }

    @PostMapping("/cadastrar")
    public String empregoForm(@Validated Emprego emprego, BindingResult result){
        if(result.hasErrors()){
            return "cadastrarEmpregos";
        }
        empregoRepository.save(emprego);
        return "redirect:/listarEmprego";
    }
}
