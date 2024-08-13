package com.example.tallerthymeleaf.thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.tallerthymeleaf.thymeleaf.repository","com.example.tallerthymeleaf.thymeleaf.model","com.example.tallerthymeleaf.thymeleaf.prueba.controllers"})
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

}
