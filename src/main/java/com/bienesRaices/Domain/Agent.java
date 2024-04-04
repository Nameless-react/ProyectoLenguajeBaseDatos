package com.bienesRaices.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private Users user;

    public Agent() {
        this.idAgent = Long.parseLong(RandomIdGenerator.generateRandomId());
    }

}
