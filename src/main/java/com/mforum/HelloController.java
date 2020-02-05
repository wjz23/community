package com.mforum;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
    @GetMapping("/greeting")
    public String sayHello(@RequestParam(name = "name",required = false,defaultValue = "world")String name, Model model){
        model.addAttribute("name",name);
        return "hello";
    }
}
