package com.example.ProyectoWeb.entrega1.prueba.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ProyectoWeb.entrega1.service.ServicioRegistro;
import com.example.ProyectoWeb.entrega1.dto.RegistroDTO;
import com.example.ProyectoWeb.entrega1.exception.CamposInvalidosException;
import com.example.ProyectoWeb.entrega1.exception.CorreoRegistradoException;
import com.example.ProyectoWeb.entrega1.model.Usuario;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/crear-cuenta")
public class ControladorRegistro {
    
    private final ServicioRegistro servicioRegistro;

    public ControladorRegistro(ServicioRegistro servicioRegistro) {
        this.servicioRegistro = servicioRegistro;
    }
    
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO) {
        try {

            Usuario usuario = servicioRegistro.registerUser(registroDTO);
            return ResponseEntity.ok(usuario);

        } catch (CorreoRegistradoException e) {

            // Crear un objeto JSON para el mensaje de error
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            
        } catch(CamposInvalidosException e)
        {
            // Crear un objeto JSON para el mensaje de error
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

        } catch (Exception e) {
            // Crear un objeto JSON para un error genérico
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Hubo un problema con el registro. Por favor, inténtelo de nuevo más tarde.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
