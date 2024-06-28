package com.cloud.my.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudMyServiceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudMyServiceDemoApplication.class, args);
	}

}
