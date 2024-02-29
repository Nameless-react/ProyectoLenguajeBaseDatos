package com.bienesRaices.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/properties")
public class PropertyController {
    @GetMapping("/list")
    public String list(Model model) {
        return "/properties/listProperties";
    }
}
