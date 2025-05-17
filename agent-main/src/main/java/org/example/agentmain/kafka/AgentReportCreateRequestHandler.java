package org.example.agentmain.kafka;

import org.example.agentmain.dto.AgentReportDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AgentReportCreateRequestHandler extends AbstractKafkaRequestHandler<AgentReportDTO> {
    @Value("${spring.kafka.topics.agent-report-request-create}")
    private String topic;
    private final Map<String, CompletableFuture<AgentReportDTO>> responseMapAgentReports = new ConcurrentHashMap<>();

    public AgentReportCreateRequestHandler(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    protected String getTopic() {
        return topic;
    }

    @Override
    protected Map<String, CompletableFuture<AgentReportDTO>> getRequestMap() {
        return responseMapAgentReports;
    }
}
