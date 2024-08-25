package com.example.ProyectoWeb.entrega1.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoWeb.entrega1.dto.PropiedadDTO;
import com.example.ProyectoWeb.entrega1.exception.CorreoRegistradoException;
import com.example.ProyectoWeb.entrega1.exception.PropRegistradaException;
import com.example.ProyectoWeb.entrega1.model.Propiedades;
import com.example.ProyectoWeb.entrega1.repository.RepositorioPropiedades;

@Service
public class ServicioPropiedad {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RepositorioPropiedades repositorioPropiedades;

     public Propiedades savePropiedad(PropiedadDTO propiedadDTO, int id) throws PropRegistradaException{
        Propiedades propiedad = modelMapper.map(propiedadDTO, Propiedades.class);
        if(!repositorioPropiedades.propiedadDitto(id,propiedadDTO.getNombrePropiedad()))
        {
            return repositorioPropiedades.save(propiedad);
        }
        else
        {
            throw new PropRegistradaException("La propiedad ya fue registrada por usted");
        }
     }
}
