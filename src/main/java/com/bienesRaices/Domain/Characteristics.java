package com.bienesRaices.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Characteristics_Property")
public class Characteristics implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_characteristics_property")
    private Long idCharacteristicProperty;

    private Integer numberRooms;
    private Integer numberBathrooms;
    private String description;
    private Boolean garage;
    private Boolean pool;

    public Characteristics(Integer numberRooms, Integer numberBathrooms, String description, Boolean garage, Boolean pool) {
        this.numberRooms = numberRooms;
        this.numberBathrooms = numberBathrooms;
        this.description = description;
        this.garage = garage;
        this.pool = pool;
    }
}
