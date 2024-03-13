package com.bienesRaices.Dao;

import com.bienesRaices.Domain.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentDao extends JpaRepository<Agent, Long> {
}
