package com.example.ProyectoWeb.test;
import com.example.ProyectoWeb.entrega1.dto.PropiedadDTO;
import com.example.ProyectoWeb.entrega1.exception.CamposInvalidosException;
import com.example.ProyectoWeb.entrega1.exception.PropNoEncontradaException;
import com.example.ProyectoWeb.entrega1.exception.PropRegistradaException;
import com.example.ProyectoWeb.entrega1.model.Propiedades;
import com.example.ProyectoWeb.entrega1.prueba.controllers.ControladorArrendador;
import com.example.ProyectoWeb.entrega1.service.ServicioPropiedad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Collections;


class ControladorArrendadorTest {

    @Mock
    private ServicioPropiedad servicioPropiedad;

    @InjectMocks
    private ControladorArrendador controladorArrendador;

    @Mock
    private Model model;

    private PropiedadDTO propiedadDTO;
    private Propiedades propRet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializar los objetos con valores específicos
        propiedadDTO = new PropiedadDTO(
            1,
            "casa del shitpost",
            "A",
            "Medellín",
            "Alquiler",
            "Hermosa casa con vista al lago y amplios espacios.",
            3,
            2,
            true,
            false,
            true,
            120.50f
        );

        propRet = new Propiedades(
            1,
            1,
            "casa del shitpost",
            "A",
            "Medellín",
            "Alquiler",
            "Hermosa casa con vista al lago y amplios espacios.",
            3,
            2,
            true,
            false,
            true,
            120.50f
        );
    }

    @Test
    void testRegistrarPropiedad() throws PropRegistradaException, CamposInvalidosException {
        int id = 1;
        propiedadDTO.setIdArrendador(id);

        when(servicioPropiedad.savePropiedad(any(PropiedadDTO.class))).thenReturn(propRet);

        ResponseEntity<?> response = controladorArrendador.registrarPropiedad(id, model, propiedadDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(propRet, response.getBody());
    }

    @Test
    void testGetAllProperties() {
        int id = 1;
        Iterable<Propiedades> propiedades = Collections.singletonList(propRet);

        when(servicioPropiedad.getPropiedades(id)).thenReturn(propiedades);

        // Call the method
        String viewName = controladorArrendador.getAllProperties(id, model);

        // Verify the view name
        assertEquals("propiedades", viewName);

        // Verify that the model was updated
        verify(model).addAttribute("propiedades", propiedades);
        verify(model).addAttribute("id", id);
    }

    @Test
    void testModificarPropiedad() throws PropNoEncontradaException, CamposInvalidosException {
        int id = 1;
        int propId = 10;
        propiedadDTO.setIdArrendador(id);

        when(servicioPropiedad.modifyPropiedad(any(PropiedadDTO.class), eq(propId))).thenReturn(propRet);

        ResponseEntity<?> response = controladorArrendador.modificarPropiedad(id, propId, model, propiedadDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(propRet, response.getBody());
    }

    @Test
    void testRegistrarPropiedadException() throws PropRegistradaException, CamposInvalidosException {
        int id = 1;
        propiedadDTO.setIdArrendador(id);

        when(servicioPropiedad.savePropiedad(any(PropiedadDTO.class))).thenThrow(new RuntimeException("Error"));

        ResponseEntity<?> response = controladorArrendador.registrarPropiedad(id, model, propiedadDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error al registrar la propiedad: Error", response.getBody());
    }

    @Test
    void testGetAllPropertiesException() {
        int id = 1;

        // Configura el mock para lanzar una excepción cuando se intente obtener propiedades
        when(servicioPropiedad.getPropiedades(id)).thenThrow(new RuntimeException("Error"));

        // Llama al método del controlador
        String viewName = controladorArrendador.getAllProperties(id, model);

        // Verifica que el nombre de la vista sea "error"
        assertEquals("error", viewName);

        // Verifica que el modelo fue actualizado con el mensaje de error
        verify(model).addAttribute("error", "Error al obtener las propiedades: Error");
    }

    @Test
    void testModificarPropiedadException() throws PropNoEncontradaException, CamposInvalidosException {
        int id = 1;
        int propId = 10;
        propiedadDTO.setIdArrendador(id);

        when(servicioPropiedad.modifyPropiedad(any(PropiedadDTO.class), eq(propId))).thenThrow(new RuntimeException("Error"));

        ResponseEntity<?> response = controladorArrendador.modificarPropiedad(id, propId, model, propiedadDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error al modificar la propiedad: Error", response.getBody());
    }

    @Test
    void testMostrarPanelArrendador() {
        int id = 1;
        
        // Call the method
        String viewName = controladorArrendador.mostrarPanelArrendador(id, model);
        
        // Verify the view name
        assertEquals("pantallaArrendador", viewName);
        
        // Verify that the model was updated
        verify(model).addAttribute("id", id);
    }

    @Test
    void testMostrarFormularioCrearPropiedad() {
        int id = 1;
        
        // Call the method
        String viewName = controladorArrendador.mostrarFormularioCrearPropiedad(id, model);
        
        // Verify the view name
        assertEquals("crear-propiedad", viewName);
        
        // Verify that the model was updated
        verify(model).addAttribute("id", id);
    }

    @Test
    void testMostrarDetallePropiedad() {
        int id = 1;
        int propiedadId = 10;
        
        // Call the method
        String viewName = controladorArrendador.mostrarDetallePropiedad(id, propiedadId, model);
        
        // Verify the view name
        assertEquals("detalle-propiedad", viewName);
        
        // Verify that the model was updated
        verify(model).addAttribute("id", id);
        verify(model).addAttribute("propiedadId", propiedadId);
    }

    @Test
    void testSolicitarArriendoConFechaInvalida() {
        int id = 1;
        int propiedadId = 10;
        LocalDate fechaInicio = LocalDate.now().minusDays(1);
        LocalDate fechaFin = LocalDate.now().plusDays(1);
        int cantidadPersonas = 4;
        
        // Call the method
        String viewName = controladorArrendador.solicitarArriendo(id, propiedadId, fechaInicio, fechaFin, cantidadPersonas, model);
        
        // Verify that the model was updated with an error message
        verify(model).addAttribute("error", "La fecha inicial no puede ser anterior a la fecha actual.");
        
        // Verify the view name
        assertEquals("detalle-propiedad", viewName);
    }

    @Test
    void testSolicitarArriendoConFechasValidas() {
        int id = 1;
        int propiedadId = 10;
        LocalDate fechaInicio = LocalDate.now().plusDays(1);
        LocalDate fechaFin = LocalDate.now().plusDays(5);
        int cantidadPersonas = 4;
        
        // Call the method
        String viewName = controladorArrendador.solicitarArriendo(id, propiedadId, fechaInicio, fechaFin, cantidadPersonas, model);
        
        // Verify the redirect URL
        assertEquals("redirect:/arrendatario/" + id + "/solicitudes", viewName);
    }



}