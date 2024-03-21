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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    //Try to delete a user using hi id
    @GetMapping("/delete/{idUser}")
    //With sendind a RQedirect Atributes to can manage the errors or the succsess event
    public String userDelete(@PathVariable Long idUser, RedirectAttributes redirectAttributes) {
        //if the user has no role specified in the table rol and the user exists then try
        try {
            User user = userService.findById(idUser);
            userService.delete(user);
            //and redirect the user to  message that alert when the user can be deleted
            redirectAttributes.addFlashAttribute("successMessage", "Usuario eliminado correctamente");
        } catch (DataIntegrityViolationException e) {
            //when the user cant be deleted because he has a rol specified in the table rol then we goin to send a alert to the admin user
            //that he cant delete the user by a flasshAtribute
            redirectAttributes.addFlashAttribute("errorMessage", "No se puede eliminar el usuario debido a restricciones de integridad referencial");
        }
        return "redirect:/user/userList";
    }

    @GetMapping("/modify/{idUser}")
    public String modifyUser(User user, Model model) {
        user = userService.getUser(user);
        model.addAttribute("user", user);
        return "/user/modify";
    }

}
