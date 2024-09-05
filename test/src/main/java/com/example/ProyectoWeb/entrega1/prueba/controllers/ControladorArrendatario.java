package com.example.ProyectoWeb.entrega1.prueba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorArrendatario {

    // Mapping for the root or home page
    @GetMapping("/arrendatario/{id}")
    public String inicio() {
        return "inicio"; // This returns the inicio.html template
    }
}
