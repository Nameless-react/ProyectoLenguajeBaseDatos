/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bienesRaices.Controllers;

import com.bienesRaices.Domain.User;
import com.bienesRaices.Services.FireBaseStorageService;
import com.bienesRaices.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.Random;

/**
 *
 * @author arjoz
 */
@Controller
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/userList")
    public String userList(Model model) {
        var users = userService.getUsers();
        model.addAttribute("users", users);
        return "/user/list";
    }

    @GetMapping("/new")
    public String usuarioNuevo(User user) {
        return "/user/modify";
    }

    @Autowired
    private FireBaseStorageService firebaseStorageService;

    @PostMapping("/save")
    public String usuarioGuardar(User user,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
          

            userService.save(user, false);
            user.setImage(
                    firebaseStorageService.loadImage(
                            imagenFile,
                            "user",
                            user.getIdUser())
            );

        }

        userService.save(user, true);
        return "redirect:/user/userList";
    }

    @GetMapping("/delete/{idUser}")
    public String userDelete(User user) {

        userService.delete(user);
        return "redirect:/user/userList";
    }

    @GetMapping("/modify/{idUser}")
    public String modifyUser(User user, Model model) {
        user = userService.getUser(user);
        model.addAttribute("user", user);
        return "/user/modify";
    }

}
