package com.example.tallerthymeleaf.thymeleaf.prueba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RedirigirAlPrincipal {

    @GetMapping("/")
    public RedirectView redirigirAlTeam() {
        return new RedirectView("/taller-thymeleaf/equipo");
    }
}