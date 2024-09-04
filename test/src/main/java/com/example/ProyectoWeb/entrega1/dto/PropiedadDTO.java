package com.example.ProyectoWeb.entrega1.dto;

import com.example.ProyectoWeb.entrega1.model.PropiedadTemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PropiedadDTO extends PropiedadTemplate{
    public PropiedadDTO(int idArrendador, String nombrePropiedad, String departamento, String municipio, 
    String tipoIngreso, String descripcion, int cantidadHabitaciones, int cantidadBaños, 
    boolean permiteMascotas, boolean tienePiscina, boolean tieneAsador, float valorNoche) 
    {
        super(idArrendador, nombrePropiedad, departamento, municipio, tipoIngreso, descripcion, 
        cantidadHabitaciones, cantidadBaños, permiteMascotas, tienePiscina, tieneAsador, valorNoche);    
    }
}