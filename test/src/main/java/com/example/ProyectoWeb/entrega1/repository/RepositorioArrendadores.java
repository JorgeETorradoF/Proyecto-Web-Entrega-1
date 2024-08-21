package com.example.ProyectoWeb.entrega1.repository;

import com.example.ProyectoWeb.entrega1.model.Arrendadores;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioArrendadores extends CrudRepository<Arrendadores, Integer> {
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Arrendadores a WHERE a.correo = :correo")
    boolean existsByCorreo(@Param("correo") String correo);
}
