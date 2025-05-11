package org.example.agentreport.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.agentreport.entity.AgentReport;
import org.example.agentreport.service.AgentReportService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentReportConsumer {
    private final AgentReportService agentReportService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    @Value("${spring.kafka.topics.agent-report-response-create}")
    private String topicResponse;

    @KafkaListener(topics = "agent-report-request-create", groupId = "my-group")
    public void processRequest(ConsumerRecord<String, String> record) {
        String requestId = record.key();
        String agentReportString = record.value();

        System.out.println("Received request from Kafka: " + requestId);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            AgentReport agentReport = objectMapper.readValue(agentReportString, AgentReport.class);
            AgentReport reportResult =  agentReportService.save(agentReport);
            kafkaTemplate.send(topicResponse, requestId, objectMapper.writeValueAsString(reportResult));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
