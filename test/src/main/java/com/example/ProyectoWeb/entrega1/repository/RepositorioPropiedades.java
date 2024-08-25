package com.example.ProyectoWeb.entrega1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ProyectoWeb.entrega1.model.Propiedades;

@Repository
public interface RepositorioPropiedades extends CrudRepository<Propiedades, Integer> {
    
}
