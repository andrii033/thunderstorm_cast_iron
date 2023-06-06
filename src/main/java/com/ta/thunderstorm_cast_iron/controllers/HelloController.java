package com.ta.thunderstorm_cast_iron.controllers;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @PostConstruct
    public void postConstruct(){
        System.out.println("HelloController bean created");
    }
    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name", "Game start");
        return "hello";
    }
}
