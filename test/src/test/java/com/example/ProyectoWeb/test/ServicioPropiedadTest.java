package com.example.ProyectoWeb.test;

import com.example.ProyectoWeb.entrega1.dto.PropiedadDTO;
import com.example.ProyectoWeb.entrega1.exception.CamposInvalidosException;
import com.example.ProyectoWeb.entrega1.exception.PropNoEncontradaException;
import com.example.ProyectoWeb.entrega1.exception.PropRegistradaException;
import com.example.ProyectoWeb.entrega1.model.Propiedades;
import com.example.ProyectoWeb.entrega1.repository.RepositorioPropiedades;
import com.example.ProyectoWeb.entrega1.service.ServicioPropiedad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class ServicioPropiedadTest {

    @InjectMocks
    private ServicioPropiedad servicioPropiedad;

    @Mock
    private RepositorioPropiedades repositorioPropiedades;

    @Mock
    private ModelMapper modelMapper;

    private PropiedadDTO propiedadDTO;

    @BeforeEach
   void setUp() {
        propiedadDTO = new PropiedadDTO();
        propiedadDTO.setIdArrendador(1);
        propiedadDTO.setNombrePropiedad("Casa Bonita");
        propiedadDTO.setDepartamento("Antioquia");
        propiedadDTO.setMunicipio("Medellín");
        propiedadDTO.setTipoIngreso("Alquiler");
        propiedadDTO.setDescripcion("Una casa bonita en Medellín");
        propiedadDTO.setCantidadHabitaciones(3);
        propiedadDTO.setCantidadBaños(2);
        propiedadDTO.setValorNoche(100000);
    }

    @Test
   void testCheckCamposPropiedad_AllFieldsValid() {
        assertTrue(servicioPropiedad.checkCamposPropiedad(propiedadDTO));
    }

    @Test
   void testCheckCamposPropiedad_NullDTO() {
        assertFalse(servicioPropiedad.checkCamposPropiedad(null));
    }

    @Test
    void testCheckCamposPropiedad_InvalidFields() {
        propiedadDTO.setNombrePropiedad("");
        assertFalse(servicioPropiedad.checkCamposPropiedad(propiedadDTO));
    }

    @Test
    void testSavePropiedad_Success() throws PropRegistradaException, CamposInvalidosException {
        when(repositorioPropiedades.propiedadDitto(anyInt(), anyString())).thenReturn(false);
        when(modelMapper.map(any(PropiedadDTO.class), eq(Propiedades.class))).thenReturn(new Propiedades());

        Propiedades savedPropiedad = new Propiedades();
        when(repositorioPropiedades.save(any(Propiedades.class))).thenReturn(savedPropiedad);

        Propiedades result = servicioPropiedad.savePropiedad(propiedadDTO);
        assertNotNull(result);
        verify(repositorioPropiedades, times(1)).save(any(Propiedades.class));
    }

    @Test
    void testSavePropiedad_PropiedadRegistradaException() {
        when(repositorioPropiedades.propiedadDitto(anyInt(), anyString())).thenReturn(true);

        assertThrows(PropRegistradaException.class, () -> {
            servicioPropiedad.savePropiedad(propiedadDTO);
        });
    }

    @Test
    void testSavePropiedad_CamposInvalidosException() {
        propiedadDTO.setNombrePropiedad("");

        assertThrows(CamposInvalidosException.class, () -> {
            servicioPropiedad.savePropiedad(propiedadDTO);
        });
    }

    @Test
    void testModifyPropiedad_Success() throws PropNoEncontradaException, CamposInvalidosException {
        when(repositorioPropiedades.propiedadPertenece(anyInt(), anyInt())).thenReturn(true);
        when(modelMapper.map(any(PropiedadDTO.class), eq(Propiedades.class))).thenReturn(new Propiedades());
        when(repositorioPropiedades.save(any(Propiedades.class))).thenReturn(new Propiedades());

        Propiedades result = servicioPropiedad.modifyPropiedad(propiedadDTO, 1);
        assertNotNull(result);
        verify(repositorioPropiedades, times(1)).save(any(Propiedades.class));
    }

    @Test
    void testModifyPropiedad_PropNoEncontradaException() {
        when(repositorioPropiedades.propiedadPertenece(anyInt(), anyInt())).thenReturn(false);

        assertThrows(PropNoEncontradaException.class, () -> {
            servicioPropiedad.modifyPropiedad(propiedadDTO, 1);
        });
    }

    @Test
    void testModifyPropiedad_CamposInvalidosException() {
        propiedadDTO.setNombrePropiedad("");

        assertThrows(CamposInvalidosException.class, () -> {
            servicioPropiedad.modifyPropiedad(propiedadDTO, 1);
        });
    }
    @Test
    void testGetPropiedades() {
        // Datos de prueba
        Propiedades propiedad1 = new Propiedades();
        Propiedades propiedad2 = new Propiedades();
        when(repositorioPropiedades.getAllById(anyInt())).thenReturn(Arrays.asList(propiedad1, propiedad2));

        Iterable<Propiedades> propiedades = servicioPropiedad.getPropiedades(1);
        assertNotNull(propiedades);
        assertTrue(propiedades.iterator().hasNext());
        verify(repositorioPropiedades, times(1)).getAllById(anyInt());
    }

}