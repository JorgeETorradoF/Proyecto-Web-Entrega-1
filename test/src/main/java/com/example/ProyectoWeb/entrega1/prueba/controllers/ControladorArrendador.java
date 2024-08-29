package com.example.ProyectoWeb.entrega1.prueba.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ProyectoWeb.entrega1.dto.PropiedadDTO;
import com.example.ProyectoWeb.entrega1.model.Propiedades;
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
    public String mostrarPanelArrendador(@PathVariable("id") int id, Model model) {
        model.addAttribute("id", id);
        return "pantallaArrendador";
    }

    @GetMapping("/crear-propiedad")
    public String mostrarFormularioCrearPropiedad(@PathVariable("id") int id, Model model) {
        model.addAttribute("id", id);
        return "crear-propiedad"; // Nombre del archivo Thymeleaf para el formulario de creación de propiedad
    }

    @PostMapping(value = "/registrar-propiedad", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registrarPropiedad(@PathVariable("id") int id, Model model,@RequestBody PropiedadDTO propiedadDTO) {
        model.addAttribute("id", id);
        propiedadDTO.setIdArrendador(id);
        try {
            // se guarda
            return ResponseEntity.ok(servicioPropiedad.savePropiedad(propiedadDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar la propiedad: " + e.getMessage());
        }
    }

    @GetMapping("/propiedades")
    public String getAllProperties(@PathVariable("id") int id, Model model) {
        try {
            // Fetch the properties
            Iterable<Propiedades> propiedades = servicioPropiedad.getPropiedades(id);
            
            // Add properties and id to the model
            model.addAttribute("propiedades", propiedades);
            model.addAttribute("id", id);
            
            // Return the name of the Thymeleaf template
            return "propiedades"; 
        } catch (Exception e) {
            // Handle any exceptions or errors
            model.addAttribute("error", "Error al obtener las propiedades: " + e.getMessage());
            return "error"; // Return an error view or handle it accordingly
        }
    }

    @PutMapping("/modificar-propiedad/{propId}")
    public ResponseEntity<?> modificarPropiedad(@PathVariable("id") int id, @PathVariable("propId") int propId,Model model,@RequestBody PropiedadDTO propiedadDTO) {
        model.addAttribute("id", id);
        model.addAttribute("propId", propId);
        propiedadDTO.setIdArrendador(id);
        try {
            // se guarda
            return ResponseEntity.ok(servicioPropiedad.modifyPropiedad(propiedadDTO, propId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al modificar la propiedad: " + e.getMessage());
        }
    }

}

    