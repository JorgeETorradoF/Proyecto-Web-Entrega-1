package com.example.ProyectoWeb.entrega1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioRegistroDTO {
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;
    private float promedio;
    private int cantiCalif;
}
