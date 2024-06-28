package com.cloud.circuit.breaker.controller;

import com.cloud.circuit.breaker.dto.Customer;
import com.cloud.circuit.breaker.service.CircuitBreakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.ServiceUnavailableException;
import java.util.List;

@RestController
@RequestMapping("/circuit")
public class CircuitBreakerController {

    @Autowired
    private CircuitBreakerService service;

    @GetMapping("/all")
    private List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

}
