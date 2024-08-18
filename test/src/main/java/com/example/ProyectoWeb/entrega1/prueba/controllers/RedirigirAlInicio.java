package com.example.ProyectoWeb.entrega1.prueba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RedirigirAlInicio {

    @GetMapping("/")
    public RedirectView redirigirAlInicio() {
        return new RedirectView("/inicio");
    }
}