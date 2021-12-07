package com.project.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    // Significa que iremos rodar em localhost:8080
    @RequestMapping("/")
    public String homeApp(){
        return "index";
    }
}
