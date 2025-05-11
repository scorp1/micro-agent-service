package org.example.policyperiod.repository;

import org.example.policyperiod.entity.PolicyPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PolicyPeriodRepository extends JpaRepository<PolicyPeriod, Long> {
    Optional<PolicyPeriod> findPolicyPeriodByPolicyNumber(String policyNumber);
    List<PolicyPeriod> findPolicyPeriodsByAgentId(Long agentId);
    List<PolicyPeriod> findPolicyPeriodsByAgentLNR(Integer agentLnr);
}
