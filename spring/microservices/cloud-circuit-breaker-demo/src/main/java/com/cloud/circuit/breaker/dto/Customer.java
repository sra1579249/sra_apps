package com.cloud.circuit.breaker.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Customer implements Serializable {

    private static final long serialVersionUID = 1l;

    private int customerId;
    private String customerName;
    private String customerType;

}
