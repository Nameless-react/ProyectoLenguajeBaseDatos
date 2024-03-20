/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bienesRaices.Dao;

import com.bienesRaices.Domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author arjoz
 */
public interface RolDao extends JpaRepository<Rol, Long>{
    
}
