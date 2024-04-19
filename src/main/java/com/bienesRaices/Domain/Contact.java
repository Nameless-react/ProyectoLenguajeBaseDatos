package com.bienesRaices.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@NamedStoredProcedureQuery(name = "Contact.insertContact",
        procedureName = "Insert_Contact", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_property", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_phone", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_email", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_message", type = String.class)
})
@Table(name = "Contact")
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContact;

    @ManyToOne
    @JoinColumn(name = "idProperty")
    private Property property;

    private String name;
    private String email;
    private String phone;
    private String message;

    public Contact(Property property, String name, String email, String phone, String message) {
        this.property = property;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.message = message;
    }
}
