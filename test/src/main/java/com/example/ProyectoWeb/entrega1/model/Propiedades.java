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
public class Propiedades extends PropiedadTemplate{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Propiedades(int id,int idArrendador, String nombrePropiedad, String departamento, String municipio, 
                       String tipoIngreso, String descripcion, int cantidadHabitaciones, int cantidadBaños, 
                       boolean permiteMascotas, boolean tienePiscina, boolean tieneAsador, float valorNoche) 
    {
        super(idArrendador, nombrePropiedad, departamento, municipio, tipoIngreso, descripcion, 
              cantidadHabitaciones, cantidadBaños, permiteMascotas, tienePiscina, tieneAsador, valorNoche);
        this.id = id;      
    }
}
