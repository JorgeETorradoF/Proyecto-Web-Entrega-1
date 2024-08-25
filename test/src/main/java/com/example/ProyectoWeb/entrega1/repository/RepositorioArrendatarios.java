package com.example.ProyectoWeb.entrega1.repository;

import com.example.ProyectoWeb.entrega1.model.Arrendatarios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioArrendatarios extends CrudRepository<Arrendatarios, Integer> {
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Arrendatarios a WHERE a.correo = :correo")
    boolean existsByCorreo(@Param("correo") String correo);

    @Query("SELECT * FROM Arrendatarios a WHERE a.correo = :correo")
    Arrendatarios findByCorreo(@Param("correo") String correo);
}
