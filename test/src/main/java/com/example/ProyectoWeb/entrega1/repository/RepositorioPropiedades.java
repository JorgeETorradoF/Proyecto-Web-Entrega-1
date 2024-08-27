package com.example.ProyectoWeb.entrega1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ProyectoWeb.entrega1.model.Propiedades;

@Repository
public interface RepositorioPropiedades extends CrudRepository<Propiedades, Integer> {
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Propiedades p WHERE p.idArrendador = :idArrendador AND p.nombrePropiedad = :nombre")
    boolean propiedadDitto(@Param("idArrendador") int idArrendador, @Param("nombre") String nombre);
    
    @Query("SELECT p FROM Propiedades p WHERE p.idArrendador = :id")
    Iterable<Propiedades> getAllById(@Param("id") int id);
}
