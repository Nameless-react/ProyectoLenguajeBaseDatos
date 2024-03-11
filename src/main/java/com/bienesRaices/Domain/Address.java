package com.bienesRaices.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@Entity
@Table(name = "Address")
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAddress;
    private String street;
    private String province;
    private String canton;
    private String othersSigns;

    public Address(String street, String province, String canton, String othersSigns) {
        this.street = street;
        this.province = province;
        this.canton = canton;
        this.othersSigns = othersSigns;
    }
}
