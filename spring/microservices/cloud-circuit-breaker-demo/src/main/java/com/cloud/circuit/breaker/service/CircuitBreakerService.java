package com.cloud.circuit.breaker.service;

import com.cloud.circuit.breaker.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class CircuitBreakerService {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;
    public List<Customer> getAllCustomers() {
        CircuitBreaker cb = null;
        cb = circuitBreakerFactory.create("slow");
        return cb.run(()->{
            final String URL = "http://localhost:8072/customer/all";
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth("configadmin","welcome3");
            HttpEntity<?> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<List<Customer>> response = restTemplate.exchange(
                    URL,
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<List<Customer>>() {});
            return response.getBody();
        },t-> Collections.emptyList());
    }

}
