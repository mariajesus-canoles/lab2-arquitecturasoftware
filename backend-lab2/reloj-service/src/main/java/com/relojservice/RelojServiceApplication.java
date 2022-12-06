package com.relojservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RelojServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelojServiceApplication.class, args);
	}

}
