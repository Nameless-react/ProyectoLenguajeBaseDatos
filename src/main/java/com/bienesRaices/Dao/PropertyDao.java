package com.bienesRaices.Dao;

import com.bienesRaices.Domain.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PropertyDao extends JpaRepository<Property, Long> {

    @Query("SELECT p FROM Property p WHERE "
            + "CONCAT(p.name, p.price, p.antiquity) LIKE %?1%")
    Page<Property> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
}
