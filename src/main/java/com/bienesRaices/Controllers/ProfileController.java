/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bienesRaices.Controllers;

import com.bienesRaices.Domain.FavoriteProperty;
import com.bienesRaices.Domain.Users;
import com.bienesRaices.Services.FavoritePropertyService;
import com.bienesRaices.Services.FireBaseStorageService;
import com.bienesRaices.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bienesRaices.Services.PropertyService;
import com.bienesRaices.Domain.Property;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 * @author arjoz
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {
        @Autowired
        private FavoritePropertyService favoritePropertyService;

        @Autowired
        private PropertyService propertyService;

        @Autowired
        private UserService userService;

        @Autowired
        private FireBaseStorageService firebaseStorageService;

    @GetMapping("/edit/{idUser}")
        public String edit(Users user, Model model) {
            user = userService.getUser(user);
            model.addAttribute("user", user);
            return "/profile/editProfile";
        }


        @GetMapping("/{User}")
        public String getAll(FavoriteProperty favoriteProperty, Model model) {
            List<FavoriteProperty> favoriteProperies = favoritePropertyService.getFavoriteProperties(favoriteProperty.getUser().getIdUser());
            List<Property> addedProperties = propertyService.getPropertiesByAgent(favoriteProperty.getUser().getIdUser());
            model.addAttribute("favoriteProperies", favoriteProperies);
            model.addAttribute("addedProperties", addedProperties);
            return "/profile/Profile";
        }

    @PostMapping("/save")
    public String usuarioGuardar(Users user,
                                 @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {

            userService.save(user, false);
            user.setImage(
                    firebaseStorageService.loadImage(
                            imagenFile,
                            "/user",
                            user.getIdUser())
            );

        }

        userService.save(user, true);
        return "redirect:/profile/" + String.valueOf(user.getIdUser());
    }
}
