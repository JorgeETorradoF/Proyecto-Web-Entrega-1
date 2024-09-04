package com.example.ProyectoWeb.test;

import com.example.ProyectoWeb.entrega1.dto.LoginDTO;
import com.example.ProyectoWeb.entrega1.dto.RespuestaLoginDTO;
import com.example.ProyectoWeb.entrega1.exception.CorreoNoExistenteException;
import com.example.ProyectoWeb.entrega1.model.Arrendadores;
import com.example.ProyectoWeb.entrega1.model.Arrendatarios;
import com.example.ProyectoWeb.entrega1.repository.RepositorioArrendadores;
import com.example.ProyectoWeb.entrega1.repository.RepositorioArrendatarios;
import com.example.ProyectoWeb.entrega1.service.ServicioLogin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServicioLoginTest {

    @Mock
    private RepositorioArrendadores repositorioArrendadores;

    @Mock
    private RepositorioArrendatarios repositorioArrendatarios;

    @InjectMocks
    private ServicioLogin servicioLogin;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginUserArrendadorExitoso() throws CorreoNoExistenteException {
        LoginDTO loginDTO = new LoginDTO("test@example.com", "password123");
        Arrendadores arrendador = new Arrendadores();
        arrendador.setCorreo("test@example.com");
        arrendador.setContraseña("password123");
        arrendador.setId(1);

        when(repositorioArrendadores.existsByCorreo(loginDTO.getCorreo())).thenReturn(true);
        when(repositorioArrendadores.findByCorreo(loginDTO.getCorreo())).thenReturn(arrendador);

        RespuestaLoginDTO respuesta = servicioLogin.loginUser(loginDTO);

        assertNotNull(respuesta);
        assertTrue(respuesta.isArrendador());
        assertEquals(1L, respuesta.getId());
    }

    @Test
    void testLoginUserArrendatarioExitoso() throws CorreoNoExistenteException {
        LoginDTO loginDTO = new LoginDTO("test@example.com", "password123");
        Arrendatarios arrendatario = new Arrendatarios();
        arrendatario.setCorreo("test@example.com");
        arrendatario.setContraseña("password123");
        arrendatario.setId(2);

        when(repositorioArrendatarios.existsByCorreo(loginDTO.getCorreo())).thenReturn(true);
        when(repositorioArrendatarios.findByCorreo(loginDTO.getCorreo())).thenReturn(arrendatario);

        RespuestaLoginDTO respuesta = servicioLogin.loginUser(loginDTO);

        assertNotNull(respuesta);
        assertFalse(respuesta.isArrendador());
        assertEquals(2L, respuesta.getId());
    }

    @Test
    void testLoginUserCorreoNoExistente() {
        LoginDTO loginDTO = new LoginDTO("nonexistent@example.com", "password123");

        when(repositorioArrendadores.existsByCorreo(loginDTO.getCorreo())).thenReturn(false);
        when(repositorioArrendatarios.existsByCorreo(loginDTO.getCorreo())).thenReturn(false);

        assertThrows(CorreoNoExistenteException.class, () -> {
            servicioLogin.loginUser(loginDTO);
        });
    }

    @Test
    void testContraseñaValida() {
        assertTrue(servicioLogin.contraseñaValida("password123"));
        assertFalse(servicioLogin.contraseñaValida(""));
    }

    @Test
    void testEmailValido() {
        assertTrue(servicioLogin.emailValido("test@example.com"));
        assertFalse(servicioLogin.emailValido("invalid-email"));
    }
}