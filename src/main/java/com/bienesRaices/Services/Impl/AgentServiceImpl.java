package com.bienesRaices.Services.Impl;

import com.bienesRaices.Dao.AgentDao;
import com.bienesRaices.Domain.Agent;
import com.bienesRaices.Services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class AgentServiceImpl implements AgentService {
    @Autowired
    private AgentDao agentDao;

    @Override
    @Transactional(readOnly = true)
    public Agent getAgent(long id) {
        return agentDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Agent> getAgents() {
        return agentDao.findAll();
    }

    @Override
    @Transactional
    public void delete(long id) {
        agentDao.deleteById(id);
    }

    @Override
    @Transactional
    public Agent save(Agent agent) {
        return agentDao.save(agent);
    }
}
