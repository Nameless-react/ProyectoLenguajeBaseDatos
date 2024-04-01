/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bienesRaices.Services.Impl;

import com.bienesRaices.Domain.Users;
import com.bienesRaices.Services.EmailService;

import com.bienesRaices.Services.SignUpService;
import com.bienesRaices.Services.UserService;
import jakarta.mail.MessagingException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author arjoz
 */
@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;  //creado en semana 4...
    @Autowired
    private FireBaseStorageServiceImpl firebaseStorageService;

    @Override
    public Model activate(Model model, String email, String password) {
        Users user = userService.getUserByEmailPassword(email, password);
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            model.addAttribute(
                    "titulo",
                    messageSource.getMessage(
                            "signUp.activate",
                            null, Locale.getDefault()));
            model.addAttribute(
                    "mensaje",
                    messageSource.getMessage(
                            "signUp.activate.error",
                            null, Locale.getDefault()));
        }
        return model;

    }

    @Override
    public Model createUser(Model model, Users user) throws Exception {
        String message;
        if (!userService.existsUserWithEmail(user.getEmail())) {

            String key = giveMeKey();
            user.setPassword(key);
            userService.save(user, true);
            sendEmailActivate(user, key);
            message = String.format(
                    messageSource.getMessage(
                            "signUp.message.email.activate",
                            null,
                            Locale.getDefault()),
                    user.getEmail());

        } else {
            message = String.format(
                    messageSource.getMessage(
                            "signUp.message.user.email",
                            null,
                            Locale.getDefault()),
                    user.getEmail());
        }
        model.addAttribute("title",
                messageSource.getMessage(
                        "signUp.activate",
                        null,
                        Locale.getDefault()
                ));

        model.addAttribute("message", message);

        return model;

    }

    @Override
    public void activate(Users user, MultipartFile imageFile) {
        var code = new BCryptPasswordEncoder();
        user.setPassword(code.encode(user.getPassword()));
        if (!imageFile.isEmpty()) {
            userService.save(user, false);
            user.setImage(
                    firebaseStorageService.loadImage(
                            imageFile,
                            "users",
                            user.getIdUser())
            );

        }

        userService.save(user, true);
    }

    @Override
    public Model rememberUser(Model model, Users user) throws MessagingException {
        String message;
        Users user2 = userService.getUserByEmail(
                user.getEmail()
        );
        if (user2 != null) {
            String key = giveMeKey();
            user2.setPassword(key);
            userService.save(user2, false);
            sendEmailActivate(user2, key);
            message = String.format(
                    messageSource.getMessage(
                            "signUp.message.email.activate",
                            null,
                            Locale.getDefault()),
                    user.getEmail()
            );
        } else {
            message = String.format(
                    messageSource.getMessage(
                            "signUp.message.user.emailExists",
                            null,
                            Locale.getDefault()),
                    user.getEmail());
        }
        model.addAttribute("title",
                messageSource.getMessage(
                        "signUp.activate",
                        null,
                        Locale.getDefault()
                ));

        model.addAttribute(
                "message",
                message);
        
            return model;
    }
    


    private String giveMeKey() {
        String drop = "ABCDEFGHIJKLMNOPQRSTUXYZabcdefghijklmnopqrstuvwxyz0123456789_*+-";
        String key = "";
        for (int i = 0; i < 40; i++) {
            key += drop.charAt((int) (Math.random() * drop.length()));
        }
        return key;
    }

    @Value("${servidor.http}")
    private String server;

    private void sendEmailActivate(Users user, String key) throws MessagingException {
        String message = messageSource.getMessage(
                "signUp.email.activate",
                null,
                Locale.getDefault()
        );
        message = String.format(message, user.getName(),
                user.getFirstSurName(), server,
                user.getEmail(), key);

        String subject = messageSource.getMessage(
                "signUp.activation.BienesRaices",
                null, Locale.getDefault()
        );

        emailService.sendEmailHtml(user.getEmail(), subject, message);

    }

}
