package com.bienesRaices.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/properties")
public class PropertyController {
    @GetMapping("/listProperties")
    public String list(Model model) {
        return "/properties/listProperties";
    }
    
    
    
    //This list was made for the  CRUD
    @GetMapping("/propertyList")
    public String propertyList(){
        return "/properties/list";
    }
}
