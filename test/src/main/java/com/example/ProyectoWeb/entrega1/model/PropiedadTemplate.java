package com.example.ProyectoWeb.entrega1.model;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PropiedadTemplate {
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
