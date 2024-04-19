package com.bienesRaices.Services.Impl;

import com.bienesRaices.Dao.AgentDao;
import com.bienesRaices.Domain.Agent;
import com.bienesRaices.Services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentDao agentDao;

    @Override
    @Transactional(readOnly = true)
    public Agent getAgent(Agent agent) {
        return agentDao.findById(agent.getIdAgent()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Agent> getAgents() {
        return agentDao.findAll();
    }

    @Override
    @Transactional
    public Agent save(Agent agent) {
        return agentDao.save(agent);
    }

    @Override
    public Agent getAgentId(long id) {
        return agentDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Agent agent) {
        agentDao.delete(agent);
    }

}
