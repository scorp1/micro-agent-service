package org.example.agentmain.service;

import lombok.RequiredArgsConstructor;
import org.example.agentmain.client.PolicyPeriodClient;
import org.example.agentmain.dto.PolicyPeriodDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PolicyPeriodClientService {

    private final PolicyPeriodClient policyPeriodClient;

    public PolicyPeriodDTO[] getAllPolicyPeriods() {
        return policyPeriodClient.getAllPolicyPeriods();
    }
 }
