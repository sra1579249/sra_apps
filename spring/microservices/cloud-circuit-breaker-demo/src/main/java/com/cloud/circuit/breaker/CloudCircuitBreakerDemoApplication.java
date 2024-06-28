package com.cloud.circuit.breaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudCircuitBreakerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudCircuitBreakerDemoApplication.class, args);
	}

}
