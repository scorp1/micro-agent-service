package org.example.policyperiod.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.policyperiod.entity.PolicyPeriod;
import org.example.policyperiod.service.PolicyPeriodService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
public class PolicyPeriodConsumer {
    private final PolicyPeriodService policyPeriodService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    @Value("${spring.kafka.topics.policy-period-response-create}")
    private String topicPolicyPeriodCreateResponse;

    @Value("${spring.kafka.topics.policy-period-get-all-response}")
    private String topicPolicyPeriodGetAllResponse;

    @KafkaListener(topics = "policy-period-request-create", groupId = "my-group")
    public void processRequest(ConsumerRecord<String, String> record) {
        String requestId = record.key();
        String agentReportString = record.value();
        System.out.println("Received request from Kafka: " + requestId);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            PolicyPeriod agentReport = objectMapper.readValue(agentReportString, PolicyPeriod.class);
            PolicyPeriod reportResult =  policyPeriodService.save(agentReport);
            kafkaTemplate.send(topicPolicyPeriodCreateResponse, requestId, objectMapper.writeValueAsString(reportResult));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @KafkaListener(topics = "policy-period-get-all-request", groupId = "my-group")
    public void getAllPolicyPeriod(ConsumerRecord<String, String> record) {
        String requestId = record.key();

        var periods = policyPeriodService.findAll();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            var agentReportsJson = objectMapper.writeValueAsString(periods);
            kafkaTemplate.send(topicPolicyPeriodGetAllResponse, requestId, agentReportsJson);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
