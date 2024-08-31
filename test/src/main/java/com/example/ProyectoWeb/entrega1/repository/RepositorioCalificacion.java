package com.example.ProyectoWeb.entrega1.repository;

import com.example.ProyectoWeb.entrega1.model.Arrendadores;
import com.example.ProyectoWeb.entrega1.model.Arrendatarios;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RepositorioCalificacion extends CrudRepository<Arrendadores, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Arrendadores a SET a.promedio = :promedio, a.cantiCalif = :cantiCalif WHERE a.id = :id")
    void actualizarCalificacionArrendador(@Param("id") Integer id, @Param("promedio") Float promedio, @Param("cantiCalif") Integer cantiCalif);

    @Modifying
    @Transactional
    @Query("UPDATE Arrendatarios a SET a.promedio = :promedio, a.cantiCalif = :cantiCalif WHERE a.id = :id")
    void actualizarCalificacionArrendatario(@Param("id") Integer id, @Param("promedio") Float promedio, @Param("cantiCalif") Integer cantiCalif);

    default void actualizarCalificacionArrendador(Arrendadores arrendador, float puntaje) {  // Cambiado a float
        int nuevaCantidadCalificaciones = arrendador.getCantiCalif() + 1;
        float nuevoPromedio = ((arrendador.getPromedio() * arrendador.getCantiCalif()) + puntaje) / nuevaCantidadCalificaciones;

        actualizarCalificacionArrendador(arrendador.getId(), nuevoPromedio, nuevaCantidadCalificaciones);
    }

    default void actualizarCalificacionArrendatario(Arrendatarios arrendatario, float puntaje) {  // Cambiado a float
        int nuevaCantidadCalificaciones = arrendatario.getCantiCalif() + 1;
        float nuevoPromedio = ((arrendatario.getPromedio() * arrendatario.getCantiCalif()) + puntaje) / nuevaCantidadCalificaciones;
        actualizarCalificacionArrendatario(arrendatario.getId(), nuevoPromedio, nuevaCantidadCalificaciones);
    }
}
