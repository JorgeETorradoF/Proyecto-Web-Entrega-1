package com.example.ProyectoWeb.entrega1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "arrendadores")
public class Arrendadores extends Usuario {

    @Column(name = "promedio", nullable = false)
    private Float promedio = 0.0f;

    @Column(name = "canti_calif", nullable = false)
    private Integer cantiCalif = 0;

    // Getters y Setters
    @Override
    public Float getPromedio() {
        return this.promedio; 
    }

    @Override
    public void setPromedio(Float promedio) {
        this.promedio = promedio; 
    }

    @Override
    public Integer getCantiCalif() {
        return this.cantiCalif; 
    }

    @Override
    public void setCantiCalif(Integer cantiCalif) {
        this.cantiCalif = cantiCalif;
    }

}

