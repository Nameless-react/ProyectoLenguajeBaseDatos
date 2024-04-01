/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bienesRaices.Controllers;

import com.bienesRaices.Domain.Users;
import com.bienesRaices.Services.SignUpService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author arjoz
 */
@Controller
@RequestMapping("/signUp")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/new")
    public String New(Model model, Users user) {
        return "/signUp/newUser";
    }

    @GetMapping("/remember")
    public String remember(Model model, Users user) {
        return "/signUp/remember";
    }

    @PostMapping("/createUser")
    public String createUser(Model model, Users user)
            throws MessagingException, Exception {
        model = signUpService.createUser(model, user);
        return "signUp/out";
    }

    @GetMapping("/activation/{user}/{idUser}")
    public String Activate(
            Model model,
             @PathVariable(value = "user") String user,
            @PathVariable(value = "idUser") String id) {

        model = signUpService.activate(model, user, id);
        if (model.containsAttribute("user")) {
            return "/signUp/activate";
        } else {
            return "/signUp/out";
        }

    }

    @PostMapping("/activate")
    public String activate(
            Users user,
            @RequestParam("imageFile") MultipartFile imageFile) {
        System.out.println("eL id del usuario es: " + user.getIdUser());
        signUpService.activate(user, imageFile);
        return "redirect:/";
    }

    @PostMapping("/rememberUser")
    public String recordarUsuario(Model model, Users user)
            throws MessagingException, Exception {
        model = signUpService.rememberUser(model, user);
        return "/signUp/out";
    }
}
