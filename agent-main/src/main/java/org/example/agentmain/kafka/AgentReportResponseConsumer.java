package org.example.agentmain.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.agentmain.dto.AgentReportDTO;
import org.example.agentmain.dto.PolicyPeriodDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgentReportResponseConsumer {
    private final AgentReportRequestHandler agentMainProducer;

    @KafkaListener(topics = "agent-report-response-create", groupId = "my-group")
    public void processResponse(ConsumerRecord<String, AgentReportDTO> record) {
        String requestId = record.key();
        AgentReportDTO response = record.value();

        System.out.println("Received response from Kafka: " + response);
        agentMainProducer.processResponse(requestId, response);
    }
}
