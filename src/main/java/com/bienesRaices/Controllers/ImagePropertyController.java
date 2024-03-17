package com.bienesRaices.Controllers;

import com.bienesRaices.Domain.ImageProperty;
import com.bienesRaices.Services.ImagePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/image-property")
public class ImagePropertyController {
    @Autowired
    private ImagePropertyService imagePropertyService;

    @DeleteMapping("delete/{idImageProperty}")
    public void delete(ImageProperty imageProperty, Model model) {
        imagePropertyService.delete(imageProperty.getIdImageProperty());
    }

}
