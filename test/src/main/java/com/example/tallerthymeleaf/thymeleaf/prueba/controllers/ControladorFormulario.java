package com.example.tallerthymeleaf.thymeleaf.prueba.controllers;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tallerthymeleaf.thymeleaf.model.Formulario;
import com.example.tallerthymeleaf.thymeleaf.repository.RepositorioFormulario;

//http://localhost:8080/
@RestController
@RequestMapping("/formulario-enviado")
public class ControladorFormulario {

    @Autowired
    private RepositorioFormulario repositorioFormulario;
    
    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public Formulario creaFormulario(@RequestBody  Formulario formulario) {
        return repositorioFormulario.save(formulario);
    }

 
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Formulario> traeFormularios() throws Exception{
        return  repositorioFormulario.findAll();
    }

    @GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Formulario> traeFormulario(@PathVariable Long id) throws Exception{
        return repositorioFormulario.findById(id); 
    }

    @CrossOrigin
    @PutMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public Formulario actualizaFromulario(@PathVariable Long id, @RequestBody Formulario formulario) throws Exception {
        return repositorioFormulario.save(formulario);
    }

    @CrossOrigin
    @DeleteMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void borraEstudiante(@PathVariable Long id) throws Exception{
        repositorioFormulario.deleteById(id);
        return;
    }

}
