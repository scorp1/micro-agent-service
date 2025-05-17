package org.example.agentmain.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.example.agentmain.dto.PolicyPeriodDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public abstract class AbstractKafkaRequestHandler<T> {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, CompletableFuture<T>> requestMap = new ConcurrentHashMap<>();

    protected abstract String getTopic(); // Разные топики
    protected abstract Map<String, CompletableFuture<T>> getRequestMap(); // Разные мапы

    public CompletableFuture<T> sendRequest(T requestData) {
        String requestId = UUID.randomUUID().toString();
        CompletableFuture<T> future = new CompletableFuture<>();
        getRequestMap().put(requestId, future);
        objectMapper.registerModule(new JavaTimeModule());

        try {
            kafkaTemplate.send(getTopic(), requestId, objectMapper.writeValueAsString(requestData));
        } catch (Exception e) {
            future.completeExceptionally(e);
        }

        System.out.println("Sent request to Kafka with ID: " + requestId);
        return future;
    }

    public void processResponse(String requestId, T response) {
        CompletableFuture<T> future = getRequestMap().remove(requestId);
        if (future != null) {
            future.complete(response);
        }
    }

    public KafkaTemplate<String, String> getKafkaTemplate() {
        return kafkaTemplate;
    }

}
