/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bienesRaices.Services.Impl;

import com.bienesRaices.Dao.RolDao;
import com.bienesRaices.Dao.UserDao;
import com.bienesRaices.Domain.Rol;
import com.bienesRaices.Domain.User;
import com.bienesRaices.Services.UserService;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author arjoz
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RolDao rolDao;

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(User user) {
        return userDao.findById(user.getIdUser()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmailPassword(String email, String password) {
        return userDao.findByEmailAndPassword(email, password);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsUserWithEmail(String email) {
        return userDao.existsByEmail(email);
    }

    @Override
    @Transactional
    public void save(User user, boolean createRolUser) {
       User existingUser = userDao.findById(user.getIdUser()).orElse(null);
       
       if(existingUser != null && user.getPassword()== null){
           user.setPassword(existingUser.getPassword());
         
       }
       if(existingUser != null && user.getBirthDate() == null) {
             user.setBirthDate(existingUser.getBirthDate());
       }
       
     
       user = userDao.save(user);
      
       if(createRolUser){
           Rol rol = new Rol();
           rol.setName("ROLE_USER");
           rol.setIdUser(user.getIdUser());
           rolDao.save(rol);
       }
    }

    @Override
    @Transactional
    public void delete(User user) {
       userDao.delete(user);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id).orElse(null);
    }


}
