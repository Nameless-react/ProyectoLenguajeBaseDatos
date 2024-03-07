package com.bienesRaices.Domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Property")
public class Property implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProperty;
    private String name;
    private Integer idCharacteristics;
    private Integer idAgent;
    private Integer idAddress;
    private Double price;
    private String transactionType;
    private Integer antiquity;
    private String owner;


    public Property(Integer idCharacteristics, String name, Integer idAgent, Integer idAddress, Double price, String transactionType, Integer antiquity, String owner) {
        this.idCharacteristics = idCharacteristics;
        this.name = name;
        this.idAgent = idAgent;
        this.idAddress = idAddress;
        this.price = price;
        this.transactionType = transactionType;
        this.antiquity = antiquity;
        this.owner = owner;
    }
}
