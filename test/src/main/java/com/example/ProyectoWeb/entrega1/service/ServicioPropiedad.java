package com.example.ProyectoWeb.entrega1.service;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoWeb.entrega1.dto.PropiedadDTO;
import com.example.ProyectoWeb.entrega1.exception.CamposInvalidosException;
import com.example.ProyectoWeb.entrega1.exception.PropNoEncontradaException;
import com.example.ProyectoWeb.entrega1.exception.PropRegistradaException;
import com.example.ProyectoWeb.entrega1.model.Propiedades;
import com.example.ProyectoWeb.entrega1.repository.RepositorioPropiedades;

@Service
public class ServicioPropiedad {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RepositorioPropiedades repositorioPropiedades;

    public boolean checkCamposPropiedad(PropiedadDTO prop) 
    {
        if (prop == null) {
            return false; //caso donde no tenga absolutamente nada
        }
    
        if (prop.getIdArrendador() <= 0) return false;
        if (prop.getNombrePropiedad() == null || prop.getNombrePropiedad().isEmpty()) return false;
        if (prop.getDepartamento() == null || prop.getDepartamento().isEmpty()) return false;
        if (prop.getMunicipio() == null || prop.getMunicipio().isEmpty()) return false;
        if (prop.getTipoIngreso() == null || prop.getTipoIngreso().isEmpty()) return false;
        if (prop.getDescripcion() == null || prop.getDescripcion().isEmpty()) return false;
        if (prop.getCantidadHabitaciones() <= 0) return false;
        if (prop.getCantidadBaños() <= 0) return false;
        if (prop.getValorNoche() <= 0) return false;
    
        // Si llega hasta aquí pasó la inspección
        
        return true;
    }
    

    public Propiedades savePropiedad(PropiedadDTO propiedadDTO) throws PropRegistradaException, CamposInvalidosException 
    {
        
        if(checkCamposPropiedad(propiedadDTO))
        {
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
        else
        {
            throw new CamposInvalidosException("No se admiten campos vacíos, intente de nuevo");
        }
    }
    
    
    public Iterable<Propiedades> getPropiedades(int id){
        return repositorioPropiedades.getAllById(id);
    }

    
    public Propiedades modifyPropiedad(PropiedadDTO propiedadDTO, int propId) throws PropNoEncontradaException, CamposInvalidosException{
        if(checkCamposPropiedad(propiedadDTO))
        {
            boolean lePertenece = repositorioPropiedades.propiedadPertenece(propiedadDTO.getIdArrendador(), propId);
            if(lePertenece)
            {
                Propiedades propRetorno = modelMapper.map(propiedadDTO, Propiedades.class);
                propRetorno.setId(propId);
                return repositorioPropiedades.save(propRetorno);
    
            }
            else
            {   
                throw new PropNoEncontradaException("No se encuentra la propiedad del usuario solicitada");
            }
        }
        else
        {
            throw new CamposInvalidosException("No se admiten campos vacíos, intente de nuevo");
        }
    }
}
