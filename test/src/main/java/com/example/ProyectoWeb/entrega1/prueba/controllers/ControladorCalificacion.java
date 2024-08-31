package com.example.ProyectoWeb.entrega1.prueba.controllers;

import com.example.ProyectoWeb.entrega1.service.ServicioCalificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calificaciones")
public class ControladorCalificacion {

    @Autowired
    private ServicioCalificacion servicioCalificacion;

    @PostMapping("/arrendador/{id}")
    public ResponseEntity<String> calificarArrendador(
        @PathVariable Integer id,
        @RequestParam Double calificacion) {
    
    try {
        servicioCalificacion.calificarArrendador(id, calificacion); 
        return ResponseEntity.ok("Calificación del arrendador actualizada exitosamente");
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}


    @PostMapping("/arrendatario/{id}")
    public ResponseEntity<String> calificarArrendatario(
            @PathVariable Integer id,
            @RequestParam Float calificacion) {  
        
        try {
            servicioCalificacion.calificarArrendatario(id, calificacion);
            return ResponseEntity.ok("Calificación del arrendatario actualizada exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
