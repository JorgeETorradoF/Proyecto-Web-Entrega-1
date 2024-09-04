package com.example.ProyectoWeb.entrega1.prueba.controllers;

import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ProyectoWeb.entrega1.dto.PropiedadDTO;
import com.example.ProyectoWeb.entrega1.model.Propiedades;
import com.example.ProyectoWeb.entrega1.service.ServicioPropiedad;

import java.time.LocalDate;


@Controller
@RequestMapping("/arrendador/{id}")
public class ControladorArrendador {

    private static final String ERROR_MSG = "error";
    
    private static final String DETALLE_PROP_HTML = "detalle-propiedad";

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
    @ResponseBody
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
            model.addAttribute(ERROR_MSG, "Error al obtener las propiedades: " + e.getMessage());
            return ERROR_MSG; // Return an error view or handle it accordingly
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

    @GetMapping("/propiedad/{propiedadId}")
    public String mostrarDetallePropiedad(@PathVariable("id") int id, @PathVariable("propiedadId") int propiedadId, Model model) {
        // Cargar los detalles de la propiedad usando el ID y pasar al modelo
        // Puedes agregar lógica para obtener la propiedad con propiedadId y validaciones según el negocio
        model.addAttribute("id", id);
        model.addAttribute("propiedadId", propiedadId);
        return DETALLE_PROP_HTML;
    }

    @PostMapping("/propiedad/{propiedadId}/solicitar-arriendo")
    public String solicitarArriendo(@PathVariable("id") int id,
                                    @PathVariable("propiedadId") int propiedadId,
                                    @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
                                    @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin,
                                    @RequestParam("cantidadPersonas") int cantidadPersonas,
                                    Model model) {
        if (fechaInicio.isBefore(LocalDate.now())) {
            model.addAttribute(ERROR_MSG, "La fecha inicial no puede ser anterior a la fecha actual.");
            return DETALLE_PROP_HTML;
        }
        if (fechaFin.isBefore(fechaInicio.plusDays(1))) {
            model.addAttribute(ERROR_MSG, "La fecha final debe ser al menos un día posterior a la fecha inicial.");
            return DETALLE_PROP_HTML;
        }

        return "redirect:/arrendatario/" + id + "/solicitudes";
    }
    

}

    