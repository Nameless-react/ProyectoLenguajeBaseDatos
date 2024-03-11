package com.bienesRaices.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User  implements Serializable {
    private static final long serialVersionUID = 1L;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public User(String name, String firstSurName, String secondSurName, String identification, String email, String phone, String image, LocalDate birthDate, String password) {
        this.name = name;
        this.firstSurName = firstSurName;
        this.secondSurName = secondSurName;
        this.identification = identification;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.birthDate = birthDate;
        this.password = password;
    }
}
