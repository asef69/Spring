package com.app.ecom;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.app.ecom", "controller", "service"})
@EnableJpaRepositories(basePackages = {"repository"})
@EntityScan(basePackages = {"model"})
public class EcomApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomApplication.class, args);
	}

}
