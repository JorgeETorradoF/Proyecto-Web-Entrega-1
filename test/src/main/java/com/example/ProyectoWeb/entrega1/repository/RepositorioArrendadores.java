package com.example.ProyectoWeb.entrega1.repository;

import com.example.ProyectoWeb.entrega1.model.Arrendador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioArrendadores extends CrudRepository<Arrendador, Integer> {
}
