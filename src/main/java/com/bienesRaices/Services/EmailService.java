/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bienesRaices.Services;

import jakarta.mail.MessagingException;

/**
 *
 * @author arjoz
 */
public interface EmailService {
    public void sendEmailHtml(
            String forTo,
            String subject,
            String contentHtml
    )throws MessagingException;
}
