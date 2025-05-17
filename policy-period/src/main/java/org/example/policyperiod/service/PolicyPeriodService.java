package org.example.policyperiod.service;

import lombok.RequiredArgsConstructor;
import org.example.policyperiod.dto.PolicyPeriodDto;
import org.example.policyperiod.entity.PolicyPeriod;
import org.example.policyperiod.repository.PolicyPeriodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PolicyPeriodService {
    private final PolicyPeriodRepository policyPeriodRepository;
    public PolicyPeriod save(PolicyPeriod policyPeriod) {
        return policyPeriodRepository.saveAndFlush(policyPeriod);
    }

    @Transactional(readOnly = true)
    public List<PolicyPeriodDto> findAll() {
        return policyPeriodRepository.findAll()
            .stream()
            .map(this::mapToDto)
            .toList();
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

    private PolicyPeriodDto mapToDto(PolicyPeriod period) {
        PolicyPeriodDto dto = new PolicyPeriodDto();
        dto.setId(period.getId());
        dto.setAgentId(period.getAgentId());
        dto.setCreateTime(period.getCreateTime());
        dto.setPeriodStart(period.getPeriodStart());
        dto.setPeriodEnd(period.getPeriodEnd());
        dto.setProductCode(period.getProductCode());
        dto.setPolicyStatus(period.getPolicyStatus());
        dto.setPolicyNumber(period.getPolicyNumber());
        dto.setAgentLNR(period.getAgentLNR());

        return dto;
    }
}
