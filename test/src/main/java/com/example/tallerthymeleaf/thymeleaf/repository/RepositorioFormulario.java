package com.example.tallerthymeleaf.thymeleaf.repository;

import com.example.tallerthymeleaf.thymeleaf.model.Formulario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioFormulario extends CrudRepository<Formulario, Long> {
    
}