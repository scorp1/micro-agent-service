package org.example.agentmain.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PolicyPeriodGetRequestHandler extends AbstractKafkaRequestHandler<String> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Value("${spring.kafka.topics.policy-period-get-all-request}")
  private String topic;
  private final Map<String, CompletableFuture<String>> responseMapPolicyPeriod = new ConcurrentHashMap<>();

  public PolicyPeriodGetRequestHandler(KafkaTemplate<String, String> kafkaTemplate) {
    super(kafkaTemplate);
  }

  public CompletableFuture<String> sendRequest() {
    String requestId = UUID.randomUUID().toString();
    CompletableFuture<String> future = new CompletableFuture<>();
    getRequestMap().put(requestId, future);
    objectMapper.registerModule(new JavaTimeModule());
    try {
      getKafkaTemplate().send(getTopic(), requestId, "policy-period-get-all");
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
    return responseMapPolicyPeriod;
  }


}
