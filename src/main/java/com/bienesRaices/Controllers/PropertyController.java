package com.bienesRaices.Controllers;

import com.bienesRaices.Domain.Property;
import com.bienesRaices.Services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/properties")
public class PropertyController {
    @Autowired
    public PropertyService propertyService;
    @GetMapping("/list")
    public String list(Model model) {
        return "/properties/list";
    }

    @GetMapping("/new")
    public String newElement(Property property, Model model) {
        return "/property/update";
    }

    @PostMapping("/save")
    public String save(Property property, Model model, @RequestParam("imageFile") MultipartFile imageFile) {

        propertyService.save(property);
        return "redirect:/property/list";
    }

    @GetMapping("/update/{id_property}")
    public String update(Property property, Model model) {
        property = propertyService.getProperty(property.getIdProperty());
        model.addAttribute("property", property);
        return "/properties/update";
    }

    @GetMapping("/delete/{id_property}")
    public String delete(Property property, Model model) {
        propertyService.delete(property.getIdProperty());
        return "redirect:/properties/list";
    }
}
