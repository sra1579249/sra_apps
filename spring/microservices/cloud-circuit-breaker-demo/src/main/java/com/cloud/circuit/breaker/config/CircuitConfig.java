package com.cloud.circuit.breaker.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;


@Configuration
public class CircuitConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public Customizer<CircuitBreakerFactory> defaultCustomizer(){
        return factory -> factory.configure(id -> new CircuitBreakerConfig.Builder()
                .failureRateThreshold(20)
                .slowCallDurationThreshold(Duration.ofSeconds(5))
                .slowCallRateThreshold(100)
                .permittedNumberOfCallsInHalfOpenState(3).build(),"slow");
    }
}
