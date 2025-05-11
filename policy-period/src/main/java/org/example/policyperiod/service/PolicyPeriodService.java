package org.example.policyperiod.service;

import lombok.RequiredArgsConstructor;
import org.example.policyperiod.entity.PolicyPeriod;
import org.example.policyperiod.repository.PolicyPeriodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PolicyPeriodService {
    private final PolicyPeriodRepository policyPeriodRepository;
    public PolicyPeriod save(PolicyPeriod policyPeriod) {
        return policyPeriodRepository.saveAndFlush(policyPeriod);
    }

    public List<PolicyPeriod> getAll() {
        return policyPeriodRepository.findAll();
    }

    public Optional<PolicyPeriod> getPolicyPeriod(String policyNumber) {
        return policyPeriodRepository.findPolicyPeriodByPolicyNumber(policyNumber);
    }

    public List<PolicyPeriod> getPolicyPeriodByAgentId(Long agentId) {
        return policyPeriodRepository.findPolicyPeriodsByAgentId(agentId);
    }

    public List<PolicyPeriod> getPolicyPeriodByAgentLnr(Integer agentLnr) {
        return policyPeriodRepository.findPolicyPeriodsByAgentLNR(agentLnr);
    }
}
