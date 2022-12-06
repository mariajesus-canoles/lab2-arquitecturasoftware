package com.horaextraservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HoraExtraServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoraExtraServiceApplication.class, args);
	}

}
