package org.example.policyperiod.api;

import lombok.RequiredArgsConstructor;
import org.example.policyperiod.dto.PolicyPeriodDto;
import org.example.policyperiod.entity.PolicyPeriod;
import org.example.policyperiod.service.PolicyPeriodService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/policy-periods")
@RequiredArgsConstructor
public class PolicyPeriodController {
    private final PolicyPeriodService service;
    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @GetMapping("/all")
    public ResponseEntity<List<PolicyPeriodDto>> getAllPolicyPeriods() {
        List<PolicyPeriodDto> periods = service.findAll();
        if (periods.size() > 0) {
            return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<PolicyPeriod> createPolicyPeriod(@RequestBody PolicyPeriod policyPeriod) {
        PolicyPeriod policy = service.save(policyPeriod);
        if (policy != null) {
            return new ResponseEntity<>(policy, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/policy-number/{policyNumber}")
    public ResponseEntity<PolicyPeriod> getPolicyPeriod(@PathVariable String policyNumber) {
        Optional<PolicyPeriod> policy = service.getPolicyPeriod(policyNumber);
        if (policy.isPresent()) {
            return new ResponseEntity<>(policy.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/agent-id/{agentId}")
    public ResponseEntity<List<PolicyPeriod>> getPolicyPeriodByAgentId(@PathVariable Long agentId) {
        List<PolicyPeriod> periods = service.getPolicyPeriodByAgentId(agentId);
        if (periods.size() > 0) {
            return new ResponseEntity<>(periods, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/test")
    public ResponseEntity<String> getInstanceId() {
        return new ResponseEntity<>(instanceId, HttpStatus.OK);
    }

    @GetMapping("/agent-lnr/{agentLnr}")
    public ResponseEntity<List<PolicyPeriod>> getPolicyPeriodByAgentLnr(@PathVariable Integer agentLnr) {
        List<PolicyPeriod> periods = service.getPolicyPeriodByAgentLnr(agentLnr);
        if (periods.size() > 0) {
            return new ResponseEntity<>(periods, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

