/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bienesRaices.Services.Impl;


import com.bienesRaices.Services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author arjoz
 */
@Service
public class EmailServiceImpl implements EmailService{
    
    @Autowired
    private JavaMailSender  mailSender;
    @Override
    public void sendEmailHtml(String forTo, String subject, String contentHtml) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(forTo);
        helper.setSubject(subject);
        helper.setText(contentHtml, true);
        mailSender.send(message);
    }
    
}
