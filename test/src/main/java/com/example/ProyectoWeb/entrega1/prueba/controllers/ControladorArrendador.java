package com.example.ProyectoWeb.entrega1.prueba.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ProyectoWeb.entrega1.dto.PropiedadDTO;
import com.example.ProyectoWeb.entrega1.service.ServicioPropiedad;

@Controller
@RequestMapping("/arrendador/{id}")
public class ControladorArrendador {

    private final ServicioPropiedad servicioPropiedad;

    public ControladorArrendador(ServicioPropiedad servicioPropiedad)
    {
        this.servicioPropiedad = servicioPropiedad;
    }


    @GetMapping
    public String mostrarPanelArrendador(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "pantallaArrendador";
    }

    @GetMapping("/crear-propiedad")
    public String mostrarFormularioCrearPropiedad(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "crear-propiedad"; // Nombre del archivo Thymeleaf para el formulario de creación de propiedad
    }

    @PostMapping(value = "/registrar-propiedad", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registrarPropiedad(@PathVariable("id") Long id, @RequestBody PropiedadDTO propiedadDTO) {
        try {
            // se guarda
            servicioPropiedad.savePropiedad(propiedadDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body("Propiedad registrada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar la propiedad: " + e.getMessage());
        }
    }

}

    