package com.bienesRaices.Services;

import com.bienesRaices.Domain.Address;
import com.bienesRaices.Domain.Agent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AgentService {
    public Agent getAgent(long id);
    public List<Agent> getAgents();
    public void delete(long id);
    public Agent save(Agent agent);
}
