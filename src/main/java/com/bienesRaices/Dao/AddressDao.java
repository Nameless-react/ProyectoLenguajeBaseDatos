package com.bienesRaices.Dao;

import com.bienesRaices.Domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDao extends JpaRepository<Address, Long> {
}
