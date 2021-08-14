package com.smrc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SmrcDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmrcDiscoveryServiceApplication.class, args);
	}

}
