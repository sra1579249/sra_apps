package com.cloud.eureka.server.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CloudEurekaServerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudEurekaServerDemoApplication.class, args);
	}

}
