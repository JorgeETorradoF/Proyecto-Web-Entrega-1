package com.example.ProyectoWeb.entrega1.prueba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorInicio {

    // Mapping for the root or home page
    @GetMapping("/inicio")
    public String inicio() {
        return "inicio"; // This returns the inicio.html template
    }

    // Mapping for the registration page
    @GetMapping("/registro")
    public String registro() {
        return "registro"; // This returns the registro.html template
    }

    // Mapping for the login page
    @GetMapping("/iniciar-sesion")
    public String iniciarSesion() {
        return "iniciar-sesion"; // This returns the iniciar-sesion.html template
    }
}
