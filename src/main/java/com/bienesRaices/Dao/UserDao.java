package com.bienesRaices.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bienesRaices.Domain.User;

public interface UserDao extends JpaRepository<User, Long> {
    
    public User findByEmail(String email);

    public User findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);
}
