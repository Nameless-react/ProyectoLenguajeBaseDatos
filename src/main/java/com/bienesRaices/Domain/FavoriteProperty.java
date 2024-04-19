package com.bienesRaices.Domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@NamedStoredProcedureQuery(name = "Favorite_Property.addFavoriteProperty",
        procedureName = "Add_Favorite_Property", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_property", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_user", type = Long.class)})
@Table(name = "Favorite_Property")
public class FavoriteProperty implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFavoriteProperty;

    @ManyToOne
    @JoinColumn(name = "idProperty")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private Users user;

    public FavoriteProperty(Property property, Users user) {
        this.property = property;
        this.user = user;
    }
}
