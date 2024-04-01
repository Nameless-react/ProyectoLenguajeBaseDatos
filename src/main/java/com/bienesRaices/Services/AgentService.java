package com.bienesRaices.Services;

import com.bienesRaices.Domain.Address;
import com.bienesRaices.Domain.Agent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AgentService {
    public Agent getAgent(Agent agent);
    public Agent getAgentId (long id);
    public List<Agent> getAgents();
    
 // Se elimina el usuario que tiene el id pasado por parámetro
    public void delete(Agent agent);
    
    public Agent save(Agent agent);
}
