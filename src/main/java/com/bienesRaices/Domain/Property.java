package com.bienesRaices.Domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @OneToOne
    @JoinColumn(name = "idCharacteristics")
    private Characteristics characteristics;

    @ManyToOne
    @JoinColumn(name = "idAgent")
    private Agent agent;

    @OneToOne
    @JoinColumn(name = "idAddress")
    private Address address;
    private Double price;
    private String transactionType;
    private Integer antiquity;
    private String owner;

    @OneToMany(mappedBy = "idProperty", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageProperty> images = new ArrayList<>();


    public Property(Characteristics characteristics, String name, Agent agent, Address address, Double price, String transactionType, Integer antiquity, String owner) {
        this.characteristics = characteristics;
        this.name = name;
        this.agent = agent;
        this.address = address;
        this.price = price;
        this.transactionType = transactionType;
        this.antiquity = antiquity;
        this.owner = owner;
    }
}
