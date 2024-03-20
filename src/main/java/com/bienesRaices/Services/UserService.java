/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bienesRaices.Services;

import com.bienesRaices.Domain.User;
import java.util.List;

/**
 *
 * @author arjoz
 */
public interface UserService {
    
    public List<User> getUsers();
    
    
    public User findById(Long id);
    public User getUser(User user);
    
    public  User getUserByEmail(String email);
    
    public User getUserByEmailPassword(String email, String password);

    public boolean existsUserWithEmail(String email);
    
    public void save(User user, boolean createRolUser);
    
    public void delete (User user);
    
}
