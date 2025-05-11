package org.example.agentmain.api;

import lombok.RequiredArgsConstructor;
import org.example.agentmain.entity.Agent;
import org.example.agentmain.service.AgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agent")
public class AgentController {
    private final AgentService agentService;

    @PostMapping("/create")
    public ResponseEntity<Agent> createAgent(@RequestBody Agent agent) {
        Agent createdAgent = agentService.save(agent);
        return new ResponseEntity<>(createdAgent, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Agent>> getAgentsAll() {
        List<Agent> agents = agentService.getAll();
        if (agents.size() > 0) {
            return new ResponseEntity<>(agents, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{agentId}")
    public ResponseEntity<Agent> getAgent(@PathVariable long agentId) {
        Optional<Agent> agent = agentService.getAgent(agentId);
        if (agent.isPresent()) {
            return new ResponseEntity<>(agent.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
