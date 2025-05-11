package org.example.apigateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircuitBreakerLoggingConfig {
    private static final Logger logger = LoggerFactory.getLogger(CircuitBreakerLoggingConfig.class);

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerRegistry registry = CircuitBreakerRegistry.ofDefaults();

        registry.getAllCircuitBreakers().forEach(circuitBreaker ->
                circuitBreaker.getEventPublisher()
                        .onStateTransition(event ->
                                logger.info("Circuit Breaker State changed: {}", event)
                        )
                        .onFailureRateExceeded(event ->
                                logger.warn("Failure rate exceeded: {}", event)
                        )
        );

        return registry;
    }
}
