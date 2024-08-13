package com.example.ProyectoWeb.entrega1.service;

import com.example.ProyectoWeb.entrega1.model.Arrendatarios;
import java.util.List;

public interface ServicioArrendatario {
    List<Arrendatarios> findAll();
    Arrendatarios findById(Integer id);
    void save(Arrendatarios arrendatarios);
    void update(Arrendatarios arrendatarios);
    void delete(Integer id);
}
