package com.example.ProyectoWeb.entrega1.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroDTO extends UsuarioRegistroDTO {
    private boolean arrendador;
    public RegistroDTO(String nombre, String apellido, String correo, String contraseña, boolean arrendador) {
        super(nombre, apellido, correo, contraseña,0,0); 
        this.arrendador = arrendador;
    }
}
