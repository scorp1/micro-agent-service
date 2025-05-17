package org.example.agentmain.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.agentmain.dto.PolicyPeriodDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PolicyPeriodResponseConsumer {

    private final PolicyPeriodCreateRequestHandler policyPeriodCreateRequestHandler;
    private final PolicyPeriodGetRequestHandler policyPeriodGetRequestHandler;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "policy-period-response-create", groupId = "my-group")
    public void processResponse(ConsumerRecord<String, String> record) {
        String requestId = record.key();
        try {
            objectMapper.registerModule(new JavaTimeModule());
            PolicyPeriodDTO response = objectMapper.readValue(record.value(), PolicyPeriodDTO.class);
            System.out.println("Received response from Kafka: " + response);
            policyPeriodCreateRequestHandler.processResponse(requestId, response);
        }catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    @KafkaListener(topics = "policy-period-get-all-response", groupId = "my-group")
    public void getAllPolicyPeriodResponse(ConsumerRecord<String, String> record) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        var requestId = record.key();
        var response = record.value();

        policyPeriodGetRequestHandler.processResponse(requestId, response);
    }
}
