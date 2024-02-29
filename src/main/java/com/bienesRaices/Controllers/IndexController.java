package com.bienesRaices.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        return "/index";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "/contact";
    }

    @GetMapping("/aboutUs")
    public String aboutUs(Model model) {
        return "/aboutUs";
    }
}
