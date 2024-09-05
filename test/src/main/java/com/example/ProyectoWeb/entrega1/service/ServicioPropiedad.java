package com.example.ProyectoWeb.entrega1.service;



import java.util.Optional;

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

    private String propNoEncontradaMsg = "No se encuentra la propiedad del usuario solicitada";

    //Aquí se comprueba que no haya campos vacíos
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
    
    //guarda una propiedad
    public Propiedades savePropiedad(PropiedadDTO propiedadDTO) throws PropRegistradaException, CamposInvalidosException 
    {
        
        //verificamos que no haya vacíos
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
    
    //regresa un iterable que se puede inyectar al html
    public Iterable<Propiedades> getPropiedades(int id){
        return repositorioPropiedades.getAllById(id);
    }
    //regresa una propiedad del usuario
    public Propiedades getPropiedad(int propId, int id) throws PropNoEncontradaException{
        if(repositorioPropiedades.propiedadPertenece(id,propId))
        {
            Optional<Propiedades> propRet = repositorioPropiedades.findById(id);
            if (propRet.isPresent()) {
                // Si se encuentra la propiedad, la retornamos
                return propRet.get();
            } 
            else {
                throw new PropNoEncontradaException(propNoEncontradaMsg);
            }
    
        }
        else
        {
            throw new PropNoEncontradaException(propNoEncontradaMsg);
        }
    }

    //función de modificación de propiedad
    public Propiedades modifyPropiedad(PropiedadDTO propiedadDTO, int propId) throws PropNoEncontradaException, CamposInvalidosException{
        
        //verificamos que no haya vacíos
        if(checkCamposPropiedad(propiedadDTO))
        {
            //verificamos que la propiedad a modificar efectivamente le pertenezca al arrendador solo por agregar un poco de seguridad (en el 3er corte veremos seguridad así que no nos vamos a complicar mucho por ahora)
            boolean lePertenece = repositorioPropiedades.propiedadPertenece(propiedadDTO.getIdArrendador(), propId);
            if(lePertenece)
            {
                Propiedades propRetorno = modelMapper.map(propiedadDTO, Propiedades.class);
                propRetorno.setId(propId);
                return repositorioPropiedades.save(propRetorno);
    
            }
            else
            {   
                //mensaje de que la propiedad no le pertenece
                throw new PropNoEncontradaException(propNoEncontradaMsg);
            }
        }
        else
        {
            //mensaje de que ingresó vacíos
            throw new CamposInvalidosException("No se admiten campos vacíos, intente de nuevo");
        }
    }
}
