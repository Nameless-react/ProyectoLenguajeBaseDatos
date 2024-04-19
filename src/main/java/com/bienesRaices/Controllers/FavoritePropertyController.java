package com.bienesRaices.Controllers;


import com.bienesRaices.Services.FavoritePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.bienesRaices.Domain.FavoriteProperty;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/favorite-properties")
public class FavoritePropertyController {
    @Autowired
    private FavoritePropertyService favoritePropertyService;

    @DeleteMapping("/{idFavoriteProperty}")
    private void delete(FavoriteProperty favoriteProperty, Model model) {
        favoritePropertyService.delete(favoriteProperty.getIdFavoriteProperty());
    }

    @GetMapping("/add")
    private String add(FavoriteProperty favoriteProperty, Model model, @RequestParam("idProperty") Long idProperty, @RequestParam("idUser") Long idUser) {
        System.out.println(idUser);
        favoritePropertyService.save(idProperty, idUser);

        return "redirect:/properties/" + String.valueOf(idProperty);
    }
}
