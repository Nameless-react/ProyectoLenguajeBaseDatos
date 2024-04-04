package com.bienesRaices.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bienesRaices.Domain.Users;

public interface UserDao extends JpaRepository<Users, Long> {
    

    
    public Users findByEmail(String email);

    public Users findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);
}
