package com.smrc.gateone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
public class GateOneServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateOneServiceApplication.class, args);
	}

}
