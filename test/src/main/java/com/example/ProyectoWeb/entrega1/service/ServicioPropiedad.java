package com.example.ProyectoWeb.entrega1.service;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoWeb.entrega1.dto.PropiedadDTO;
import com.example.ProyectoWeb.entrega1.exception.PropRegistradaException;
import com.example.ProyectoWeb.entrega1.model.Propiedades;
import com.example.ProyectoWeb.entrega1.repository.RepositorioPropiedades;

@Service
public class ServicioPropiedad {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RepositorioPropiedades repositorioPropiedades;

    public Propiedades savePropiedad(PropiedadDTO propiedadDTO) throws PropRegistradaException {
        // Verifica si la propiedad ya está registrada
        boolean propiedadRegistrada = repositorioPropiedades.propiedadDitto(propiedadDTO.getIdArrendador(), propiedadDTO.getNombrePropiedad());
    
        if (propiedadRegistrada) {
            throw new PropRegistradaException("La propiedad ya fue registrada por usted");
        }
    
        // Mapear el DTO a una entidad
        Propiedades propiedades = modelMapper.map(propiedadDTO, Propiedades.class);
        
        // Si la propiedad no está registrada, guárdala
        return repositorioPropiedades.save(propiedades);
    }
    
    
    public Iterable<Propiedades> getPropiedades(int id){
        return repositorioPropiedades.getAllById(id);
    }
}
