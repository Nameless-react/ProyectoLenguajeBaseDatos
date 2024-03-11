package com.bienesRaices.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@Entity
@Table(name = "Agent")
public class Agent implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgent;

    private Double salary;
    private Integer experience;
    private LocalDate hireDate;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;


    public Agent(Double salary, Integer experience, LocalDate hireDate, User user) {
        this.salary = salary;
        this.experience = experience;
        this.hireDate = hireDate;
        this.user = user;
    }
}
