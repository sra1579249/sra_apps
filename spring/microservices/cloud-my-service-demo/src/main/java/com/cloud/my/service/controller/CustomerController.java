package com.cloud.my.service.controller;

import com.cloud.my.service.dto.Customer;
import com.cloud.my.service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/all")
    private CompletableFuture<List<Customer>> findAll(){
        return service.getAllCustomers();
    }
}
