package com.bienesRaices.Dao;

import com.bienesRaices.Domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface AddressDao extends JpaRepository<Address, Long> {
}
