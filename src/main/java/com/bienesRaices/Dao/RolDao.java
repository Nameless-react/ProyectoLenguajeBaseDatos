/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bienesRaices.Dao;

import com.bienesRaices.Domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author arjoz
 */
public interface RolDao extends JpaRepository<Rol, Long>{
    @Procedure(name = "Rol.addRol")
    public void addRol(@Param("p_name") String p_name, @Param("p_id_user") Long p_id_user);
}
