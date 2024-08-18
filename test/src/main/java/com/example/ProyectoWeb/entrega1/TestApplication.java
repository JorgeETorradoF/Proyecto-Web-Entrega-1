package com.example.ProyectoWeb.entrega1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.ProyectoWeb.entrega1.service","com.example.ProyectoWeb.entrega1.config","com.example.ProyectoWeb.entrega1.dto","com.example.ProyectoWeb.entrega1.repository","com.example.ProyectoWeb.entrega1.model","com.example.ProyectoWeb.entrega1.prueba.controllers"})
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

}
