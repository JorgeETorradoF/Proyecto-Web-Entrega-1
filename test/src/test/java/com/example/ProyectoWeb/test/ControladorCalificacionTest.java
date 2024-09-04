package com.example.ProyectoWeb.test;

import com.example.ProyectoWeb.entrega1.prueba.controllers.ControladorCalificacion;
import com.example.ProyectoWeb.entrega1.service.ServicioCalificacion;
import com.example.ProyectoWeb.entrega1.model.Arrendadores; 
import com.example.ProyectoWeb.entrega1.model.Arrendatarios; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class ControladorCalificacionTest {

    @InjectMocks
    private ControladorCalificacion controladorCalificacion;

    @Mock
    private ServicioCalificacion servicioCalificacion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalificarArrendadorSuccess() {
        Integer id = 1;
        Double calificacion = 4.5;
        Arrendadores arrendador = new Arrendadores(); // Crea o utiliza una instancia de Arrendador
        when(servicioCalificacion.calificarArrendador(anyInt(), anyDouble())).thenReturn(arrendador);

        ResponseEntity<?> response = controladorCalificacion.calificarArrendador(id, calificacion);

        assertEquals(200, response.getStatusCode());
        assertEquals(arrendador, response.getBody()); // Compara con el objeto esperado
    }

    @Test
    void testCalificarArrendadorFailure() {
        Integer id = 1;
        Double calificacion = 4.5;
        when(servicioCalificacion.calificarArrendador(anyInt(), anyDouble())).thenThrow(new RuntimeException("Error al calificar"));

        ResponseEntity<?> response = controladorCalificacion.calificarArrendador(id, calificacion);

        assertEquals(400, response.getStatusCode());
        assertEquals("Error al calificar", response.getBody()); // Esto puede necesitar ajuste si el manejo de errores cambia
    }

    @Test
    void testCalificarArrendatarioSuccess() {
        Integer id = 1;
        Float calificacion = 4.0f;
        Arrendatarios arrendatario = new Arrendatarios(); // Crea o utiliza una instancia de Arrendatario
        when(servicioCalificacion.calificarArrendatario(anyInt(), anyFloat())).thenReturn(arrendatario);

        ResponseEntity<?> response = controladorCalificacion.calificarArrendatario(id, calificacion);

        assertEquals(200, response.getStatusCode());
        assertEquals(arrendatario, response.getBody()); // Compara con el objeto esperado
    }

    @Test
    void testCalificarArrendatarioFailure() {
        Integer id = 1;
        Float calificacion = 4.0f;
        when(servicioCalificacion.calificarArrendatario(anyInt(), anyFloat())).thenThrow(new RuntimeException("Error al calificar"));

        ResponseEntity<?> response = controladorCalificacion.calificarArrendatario(id, calificacion);

        assertEquals(400, response.getStatusCode());
        assertEquals("Error al calificar", response.getBody()); // Esto puede necesitar ajuste si el manejo de errores cambia
    }
}
