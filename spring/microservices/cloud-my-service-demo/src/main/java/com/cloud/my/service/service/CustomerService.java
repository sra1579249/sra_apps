package com.cloud.my.service.service;

import com.cloud.my.service.dao.CustomerDao;
import com.cloud.my.service.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao dao;

    @Async
    public CompletableFuture<List<Customer>> getAllCustomers(){
        Executor executor = CompletableFuture.delayedExecutor((int)(Math.random()*3000), TimeUnit.MILLISECONDS,
                Executors.newSingleThreadExecutor());
        return CompletableFuture.supplyAsync(() -> dao.findAll(), executor);
    }
}
