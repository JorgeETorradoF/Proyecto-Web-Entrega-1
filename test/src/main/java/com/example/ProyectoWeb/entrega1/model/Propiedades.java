package com.example.ProyectoWeb.entrega1.model;


import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Propiedades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
