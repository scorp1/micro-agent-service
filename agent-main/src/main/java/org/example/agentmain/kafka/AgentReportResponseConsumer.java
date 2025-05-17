package org.example.agentmain.kafka;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.agentmain.dto.AgentReportDTO;
import org.example.agentmain.dto.PolicyPeriodDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgentReportResponseConsumer {
    private final AgentReportCreateRequestHandler agentReportCreateRequestHandler;
    private final AgentReportGetRequestHandler agentReportGetRequestHandler;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "agent-report-response-create", groupId = "my-group")
    public void createAgentReportResponse(ConsumerRecord<String, String> record) {
        String requestId = record.key();
        try {
            objectMapper.registerModule(new JavaTimeModule());
            AgentReportDTO response = objectMapper.readValue(record.value(), AgentReportDTO.class);
            System.out.println("Received response from Kafka: " + response);
            agentReportCreateRequestHandler.processResponse(requestId, response);
        }catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }

    }

    @KafkaListener(topics = "agent-report-get-all-response", groupId = "my-group")
    public void getAllAgentReportResponse(ConsumerRecord<String, String> record) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        var requestId = record.key();
        var response = record.value();

        agentReportGetRequestHandler.processResponse(requestId, response);
    }
}
