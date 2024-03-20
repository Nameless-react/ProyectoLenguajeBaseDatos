package com.bienesRaices.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Data

@Entity
@Table(name = "Users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long idUser;

    private String name;
    private String firstSurName;
    private String secondSurName;
    private String identification;
    private String email;
    private String phone;
    private String image;
    private LocalDate birthDate;
    private String password;

    @OneToMany
    @JoinColumn(name = "id_usuario", updatable = false)
    private List<Rol> roles;


    public User() {
    this.idUser = Long.parseLong(RandomIdGenerator.generateRandomId());
}
}


