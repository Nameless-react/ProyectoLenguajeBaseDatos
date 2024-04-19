package com.bienesRaices.Dao;

import com.bienesRaices.Domain.ImageProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface ImagePropertyDao extends JpaRepository<ImageProperty, Long> {
    @Procedure("Add_Property_Image")
    public void addPropertyImage(@Param("p_id_property") Long p_id_property, @Param("p_image") String p_image);

    @Procedure("Delete_Image_Property")
    public void deleteImageProperty(@Param("p_id_image_property") Long p_id_image_property);
}
