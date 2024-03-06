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
    private long idProperty;
    private int idCharacteristics;
    private int idAgent;
    private int id_address;
    private int price;
    private String transactionType;
    private int antiquity;
    private String owner;

    public Property(int idCharacteristics, int idAgent, int id_address, int price, String transactionType, int antiquity, String owner) {
        this.idCharacteristics = idCharacteristics;
        this.idAgent = idAgent;
        this.id_address = id_address;
        this.price = price;
        this.transactionType = transactionType;
        this.antiquity = antiquity;
        this.owner = owner;
    }
}
