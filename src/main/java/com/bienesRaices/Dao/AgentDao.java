package com.bienesRaices.Dao;

import com.bienesRaices.Domain.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface AgentDao extends JpaRepository<Agent, Long> {
    @Procedure(name = "Agent.addAgent")
    public void addAgent(@Param("p_salary") Long p_salary,
                                    @Param("p_experience") Long p_experience,
                                    @Param("p_id_user") Long p_id_user,
                                    @Param("p_hire_date") LocalDate p_hire_date);

}
