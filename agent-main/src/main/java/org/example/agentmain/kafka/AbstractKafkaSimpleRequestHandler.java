package org.example.agentmain.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public abstract class AbstractKafkaSimpleRequestHandler<T> {
  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
  protected abstract String getTopic();

  public void sendMessage(T message) {
    String messageId = UUID.randomUUID().toString();
    try {
      String jsonMessage = objectMapper.writeValueAsString(message);
      kafkaTemplate.send(getTopic(), messageId, jsonMessage);
      System.out.println("Sent Kafka message to topic [" + getTopic() + "] with ID: " + messageId);
    } catch (Exception e) {
      System.err.println("Failed to send Kafka message: " + e.getMessage());
      e.printStackTrace();
    }
  }


}
