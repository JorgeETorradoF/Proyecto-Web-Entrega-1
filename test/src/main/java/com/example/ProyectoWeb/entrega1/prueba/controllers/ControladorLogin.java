package com.example.ProyectoWeb.entrega1.prueba.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ProyectoWeb.entrega1.service.ServicioLogin;
import com.example.ProyectoWeb.entrega1.dto.LoginDTO;
import com.example.ProyectoWeb.entrega1.dto.RespuestaLoginDTO;
import com.example.ProyectoWeb.entrega1.exception.CorreoNoExistenteException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/iniciar-sesion")
public class ControladorLogin {
    
    private final ServicioLogin servicioLogin;

    public ControladorLogin(ServicioLogin servicioLogin) {
        this.servicioLogin = servicioLogin;
    }
    
    @GetMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> LoginUsuario(@RequestBody LoginDTO loginDTO) {
        try {

            RespuestaLoginDTO login  = servicioLogin.loginUser(loginDTO);
            String ruta = new String(); 

            if (login.isArrendador()){
                ruta = "/arrendador/" + login.getId();
            } else if (!login.isArrendador()) {
                ruta = "/arrendatario/" + login.getId();
            }
            return ResponseEntity.status(HttpStatus.FOUND)  
                    .header("Location", ruta)
                    .build();

        } catch (CorreoNoExistenteException e) {

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
