package com.example.ProyectoWeb.entrega1.repository;

import com.example.ProyectoWeb.entrega1.model.Arrendatario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioArrendatarios extends CrudRepository<Arrendatario, Integer> {
}
