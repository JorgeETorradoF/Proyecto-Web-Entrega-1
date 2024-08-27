package com.example.ProyectoWeb.entrega1.prueba.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;


@Controller
@RequestMapping("/arrendatario/{id}")
public class ControladorAdministrador {

    @GetMapping("/propiedad/{propiedadId}")
    public String mostrarDetallePropiedad(@PathVariable("id") int id, @PathVariable("propiedadId") int propiedadId, Model model) {
        // Cargar los detalles de la propiedad usando el ID y pasar al modelo
        // Puedes agregar lógica para obtener la propiedad con propiedadId y validaciones según el negocio
        model.addAttribute("id", id);
        model.addAttribute("propiedadId", propiedadId);
        return "detalle-propiedad";
    }

    @PostMapping("/propiedad/{propiedadId}/solicitar-arriendo")
    public String solicitarArriendo(@PathVariable("id") int id,
                                    @PathVariable("propiedadId") int propiedadId,
                                    @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
                                    @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin,
                                    @RequestParam("cantidadPersonas") int cantidadPersonas,
                                    Model model) {
        if (fechaInicio.isBefore(LocalDate.now())) {
            model.addAttribute("error", "La fecha inicial no puede ser anterior a la fecha actual.");
            return "detalle-propiedad";
        }
        if (fechaFin.isBefore(fechaInicio.plusDays(1))) {
            model.addAttribute("error", "La fecha final debe ser al menos un día posterior a la fecha inicial.");
            return "detalle-propiedad";
        }

        return "redirect:/arrendatario/" + id + "/solicitudes";
    }
}
