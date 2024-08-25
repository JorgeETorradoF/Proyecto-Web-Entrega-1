package com.example.ProyectoWeb.entrega1.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoWeb.entrega1.dto.PropiedadDTO;
import com.example.ProyectoWeb.entrega1.model.Propiedades;
import com.example.ProyectoWeb.entrega1.repository.RepositorioPropiedades;

@Service
public class ServicioPropiedad {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RepositorioPropiedades repositorioPropiedades;

     public Propiedades savePropiedad(PropiedadDTO propiedadDTO) {
        Propiedades propiedad = modelMapper.map(propiedadDTO, Propiedades.class);
        return repositorioPropiedades.save(propiedad);
     }
}
