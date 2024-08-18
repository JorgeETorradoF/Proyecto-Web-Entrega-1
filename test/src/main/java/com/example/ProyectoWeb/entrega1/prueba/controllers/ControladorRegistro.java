package com.example.ProyectoWeb.entrega1.prueba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.example.ProyectoWeb.entrega1.service.ServicioRegistro;
import com.example.ProyectoWeb.entrega1.dto.RegistroDTO;
import com.example.ProyectoWeb.entrega1.model.Usuario;

@RestController
@RequestMapping("/crear-cuenta")
public class ControladorRegistro {
    
    @Autowired
    private ServicioRegistro servicioRegistro;
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Usuario registrarUsuario(@RequestBody RegistroDTO registroDTO) {
        return servicioRegistro.registerUser(registroDTO);
    }
    
}
