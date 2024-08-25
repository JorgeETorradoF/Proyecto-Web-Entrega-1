package com.example.ProyectoWeb.entrega1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PropiedadDTO {
    private int idArrendador;
    private String nombrePropiedad;
    private String departamento;
    private String municipio;
    private String tipoIngreso;
    private String descripcion;
    private int cantidadHabitaciones;
    private int cantidadBaños;
    private boolean permiteMascotas;
    private boolean tienePiscina;
    private boolean tieneAsador;
    private float valorNoche;
}