package com.bienesRaices.Dao;

import com.bienesRaices.Domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyDao  extends JpaRepository<Property, Long> {
}
