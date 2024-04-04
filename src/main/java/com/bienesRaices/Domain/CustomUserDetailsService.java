/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bienesRaices.Domain;
import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
/**
 *
 * @author arjoz
 */
@Data
public class CustomUserDetailsService extends User{
    
    public CustomUserDetailsService(String username, String password,String image,String firstSurName, String secondSurName, String phone   ,String identification, String name ,Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.name = name;
        this.identification = identification;
        this.image = image;
        this.firstSurName =  firstSurName;
        this.secondSurName =  secondSurName;
        this.phone = phone;
        
    }
    private final String  name ;
    private final String  identification ;
    private final String  image ;
    private final String  firstSurName ;
    private final String  secondSurName ;
    private final String phone ;

    
    
    
}
