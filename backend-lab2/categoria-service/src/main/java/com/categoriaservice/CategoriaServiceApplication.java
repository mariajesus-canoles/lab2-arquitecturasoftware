package com.categoriaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CategoriaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoriaServiceApplication.class, args);
	}

}
