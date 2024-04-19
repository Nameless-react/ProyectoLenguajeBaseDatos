/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bienesRaices.Services.Impl;

import com.bienesRaices.Dao.UserDao;
import com.bienesRaices.Domain.CustomUserDetailsService;
import com.bienesRaices.Domain.Rol;
import com.bienesRaices.Domain.Users;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.bienesRaices.Services.UserDetailsServiceUs;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author arjoz
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsServiceUs, UserDetailsService{
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private HttpSession session;
    
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userDao.findByEmail(email);
        System.out.print(user.getImage());
        if(user == null){
            throw new UsernameNotFoundException(email);
        }
        session.removeAttribute("userImage");
        session.setAttribute("userImage", user.getImage());
        
        var roles = new ArrayList<GrantedAuthority>();
        for(Rol rol : user.getRoles() ){
            roles.add(new SimpleGrantedAuthority(rol.getName()));
        }
        
       return new CustomUserDetailsService(user.getEmail(), user.getPassword(), user.getImage(), user.getFirstSurName(), user.getSecondSurName(), user.getPhone(), user.getIdUser(), user.getIdentification(), user.getName(), roles);
    }


    
}
