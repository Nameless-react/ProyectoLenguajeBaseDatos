package com.bienesRaices.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bienesRaices.Domain.User;

public interface UserDao extends JpaRepository<User, Long> {
}
