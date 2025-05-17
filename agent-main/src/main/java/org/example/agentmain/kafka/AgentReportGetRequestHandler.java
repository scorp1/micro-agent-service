package org.example.agentmain.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.example.agentmain.dto.AgentReportDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AgentReportGetRequestHandler extends AbstractKafkaRequestHandler<String> {
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Value("${spring.kafka.topics.agent-report-get-all-request}")
  private String topic;
  private final Map<String, CompletableFuture<String>> responseMapAgentReports = new ConcurrentHashMap<>();

  public AgentReportGetRequestHandler(KafkaTemplate<String, String> kafkaTemplate) {
    super(kafkaTemplate);
  }

  public CompletableFuture<String> sendRequest() {
    String requestId = UUID.randomUUID().toString();
    CompletableFuture<String> future = new CompletableFuture<>();
    getRequestMap().put(requestId, future);
    objectMapper.registerModule(new JavaTimeModule());
    try {
      getKafkaTemplate().send(getTopic(), requestId, "agent-report-get-all");
    } catch (Exception e) {
      future.completeExceptionally(e);
    }

    System.out.println("Sent request to Kafka with ID: " + requestId);
    return future;
  }

  @Override
  protected String getTopic() {
    return topic;
  }

  @Override
  protected Map<String, CompletableFuture<String>> getRequestMap() {
    return responseMapAgentReports;
  }
}
