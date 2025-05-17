package org.example.agentmain.kafka;

import org.example.agentmain.dto.PolicyPeriodDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PolicyPeriodCreateRequestHandler extends AbstractKafkaRequestHandler<PolicyPeriodDTO> {
    @Value("${spring.kafka.topics.policy-period-request-create}")
    private String topic;
    private final Map<String, CompletableFuture<PolicyPeriodDTO>> responseMapPolicyPeriods = new ConcurrentHashMap<>();

    public PolicyPeriodCreateRequestHandler(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    protected String getTopic() {
        return topic;
    }

    @Override
    protected Map<String, CompletableFuture<PolicyPeriodDTO>> getRequestMap() {
        return responseMapPolicyPeriods;
    }
}
