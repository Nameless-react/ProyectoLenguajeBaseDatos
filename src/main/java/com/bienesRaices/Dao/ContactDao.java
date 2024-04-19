package com.bienesRaices.Dao;

import com.bienesRaices.Domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface ContactDao extends JpaRepository<Contact, Long> {
    @Procedure(name = "Contact.insertContact")
    public void insertContact(@Param("p_id_property") Long p_id_property,
                                    @Param("p_name") String p_name,
                                    @Param("p_phone") String p_phone,
                                    @Param("p_email") String p_email,
                                    @Param("p_message") String p_message);

}
