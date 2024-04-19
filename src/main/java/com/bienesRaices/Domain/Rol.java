/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bienesRaices.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * @author arjoz
 */
@Data
@NoArgsConstructor
@Entity
@NamedStoredProcedureQuery(name = "Rol.addRol",
        procedureName = "Add_Rol", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_user", type = Long.class)})

@Table(name = "Rol")
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;
    private String name;
    @Column(name="id_user")
    private Long idUser;

    public Rol (String name, Long idUser) {
        this.name = name;
        this.idUser = idUser;
    }
}
