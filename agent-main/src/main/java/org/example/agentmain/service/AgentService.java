package org.example.agentmain.service;

import lombok.RequiredArgsConstructor;
import org.example.agentmain.entity.Agent;
import org.example.agentmain.repository.AgentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgentService {

    private final AgentRepository agentRepository;

    public Agent save(Agent agent) {
        return agentRepository.saveAndFlush(agent);
    }

    public Optional<Agent> getAgent(long agentId) {
        return agentRepository.findById(agentId);
    }

    public List<Agent> getAll() {
        return agentRepository.findAll(Sort.by("firstName"));
    }
}
