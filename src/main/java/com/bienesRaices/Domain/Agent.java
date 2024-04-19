package com.bienesRaices.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@NamedStoredProcedureQuery(name = "Agent.addAgent",
        procedureName = "Add_Agent", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_property", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_user", type = Long.class)})
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
