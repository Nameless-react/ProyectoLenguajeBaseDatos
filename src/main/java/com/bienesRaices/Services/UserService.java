/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bienesRaices.Services;

import com.bienesRaices.Domain.Users;
import java.util.List;

/**
 *
 * @author arjoz
 */
public interface UserService {
    
    public List<Users> getUsers();
    
    
    public Users findById(Long id);
    public Users getUser(Users user);
    public  Users getUserByEmail(String email);
    
    public Users getUserByEmailPassword(String email, String password);

    public boolean existsUserWithEmail(String email);
    
    public void save(Users user, boolean createRolUser);
    
    public void delete (Users user);


    
}
