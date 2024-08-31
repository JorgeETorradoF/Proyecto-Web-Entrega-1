package com.example.ProyectoWeb.entrega1.service;

import com.example.ProyectoWeb.entrega1.model.Arrendadores;
import com.example.ProyectoWeb.entrega1.model.Arrendatarios;
import com.example.ProyectoWeb.entrega1.repository.RepositorioArrendadores;
import com.example.ProyectoWeb.entrega1.repository.RepositorioArrendatarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioCalificacion {

    @Autowired
    private RepositorioArrendadores repositorioArrendadores;

    @Autowired
    private RepositorioArrendatarios repositorioArrendatarios;

    @Transactional
    public Arrendadores calificarArrendador(Integer id, Double nuevaCalificacion) {
    Arrendadores arrendador = repositorioArrendadores.findById(id)
        .orElseThrow(() -> new RuntimeException("Arrendador no encontrado"));

    // Verifica si promedio es null, y si lo es, asigna 0.0f
    float promedioActual = arrendador.getPromedio() != null ? arrendador.getPromedio() : 0.0f;
    int cantidadCalificaciones = arrendador.getCantiCalif();

    float nuevoPromedio = ((promedioActual * cantidadCalificaciones) + nuevaCalificacion.floatValue()) / (cantidadCalificaciones + 1);
    arrendador.setPromedio(nuevoPromedio);
    arrendador.setCantiCalif(cantidadCalificaciones + 1);

    return repositorioArrendadores.save(arrendador);
}



    @Transactional
    public Arrendatarios calificarArrendatario(Integer id, Float nuevaCalificacion) {
        Arrendatarios arrendatario = repositorioArrendatarios.findById(id)
            .orElseThrow(() -> new RuntimeException("Arrendatario no encontrado"));

        float promedioActual = arrendatario.getPromedio();
        int cantidadCalificaciones = arrendatario.getCantiCalif();

        float nuevoPromedio = ((promedioActual * cantidadCalificaciones) + nuevaCalificacion.floatValue()) / (cantidadCalificaciones + 1);
        arrendatario.setPromedio(nuevoPromedio);
        arrendatario.setCantiCalif(cantidadCalificaciones + 1);

        return repositorioArrendatarios.save(arrendatario);
    }

}

