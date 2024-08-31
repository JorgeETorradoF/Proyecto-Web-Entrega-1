package com.example.ProyectoWeb.entrega1.prueba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorGeneral {

    // Mapping del root
    @GetMapping("/inicio")
    public String inicio() {
        return "inicio"; 
    }

    // Mapping del registro
    @GetMapping("/registro")
    public String registro() {
        return "registro"; 
    }

    // Mapping del login
    @GetMapping("/iniciar-sesion")
    public String iniciarSesion() {
        return "iniciar-sesion";
    }
}
