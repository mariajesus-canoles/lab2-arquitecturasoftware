package com.reporteplanillaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ReportePlanillaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportePlanillaServiceApplication.class, args);
	}

}
