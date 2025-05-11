package org.example.agentmain.client;

import org.example.agentmain.dto.PolicyPeriodDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "policy-period")
public interface PolicyPeriodClient {

    @GetMapping("/api/policy-periods/all")
    PolicyPeriodDTO[] getAllPolicyPeriods();

    @PostMapping("/api/policy-periods/create")
    PolicyPeriodDTO createPolicyPeriod(@RequestBody PolicyPeriodDTO policyPeriod);

    @GetMapping("/api/policy-periods/agent-id/{agentId}")
    PolicyPeriodDTO getPolicyPeriodByAgentId(@PathVariable("agentId") Long agentId);

}
