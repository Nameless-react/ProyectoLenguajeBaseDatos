package com.bienesRaices.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;


@Data
@NoArgsConstructor
@Entity
@Table(name = "Image_Property")
public class ImageProperty implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImageProperty;


    @Column(name = "id_property")
    private Long idProperty;

    private String image;

    public String generateImageId() {
        return String.valueOf(Instant.now().toEpochMilli()) + "_" + String.valueOf(this.idImageProperty);
    }



    public ImageProperty(Long idProperty, String image) {
        this.idProperty = idProperty;
        this.image = image;
    }
}
