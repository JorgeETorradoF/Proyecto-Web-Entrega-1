package com.example.ProyectoWeb.entrega1.prueba.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/taller-thymeleaf")
public class TallerThymeleaf {

	 @GetMapping("/arquiProyecto")
     public String arquitecturaProyecto() {
         return "arquiProyecto";
     }

	 @GetMapping("/contactanos")
	 public String contactanos() {
		 return "contactanos";
	 }
	 @GetMapping("/desarrollo")
	 public String desarrollo() {
		 return "desarrollo";
	 }

	 @GetMapping("/descriProyecto")
	 public String descriProyecto() {
		 return "descriProyecto";
	 }

	 @GetMapping("/despliegue")
	 public String despliegue() {
		 return "despliegue";
	 }
	 @GetMapping("/equipo")
	 public String equipo() {
		 return "equipo";
	 }
	 @GetMapping("/indice")
	 public String indice() {
		 return "indice";
	 }
	 @GetMapping("/pruebas")
	 public String pruebas() {
		 return "pruebas";
	 }
	 @GetMapping("/reqs")
	 public String requisitos() {
		 return "reqs";
	 }

}
