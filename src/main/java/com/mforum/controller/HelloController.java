package com.mforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("/greeting")
    public String sayHello(@RequestParam(name = "name",required = false,defaultValue = "world")String name, Model model){
        model.addAttribute("name",name);
        System.out.println("1");
        return "index";
    }
}
