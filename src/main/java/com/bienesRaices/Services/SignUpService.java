/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bienesRaices.Services;

import com.bienesRaices.Domain.Users;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author arjoz
 */
public interface SignUpService {
    //activate user
    public Model activate (Model model, String user, String password);
    
    //create user
    public Model createUser(Model model, Users user)throws Exception;
    
    public void activate (Users user, MultipartFile imageFile);
    
    public Model rememberUser(Model model, Users user)throws Exception;
}
