package com.bienesRaices.Dao;

import com.bienesRaices.Domain.FavoriteProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface FavoritePropertyDao extends JpaRepository<FavoriteProperty, Long> {
    @Procedure("Delete_Favorite_Property")
    public void deleteFavoriteProperty(Long idFavoriteProperty);

    @Procedure(name = "Favorite_Property.addFavoriteProperty")
    public void addFavoriteProperty(@Param("p_id_property") Long p_id_property, @Param("p_id_user") Long p_id_user);
}
