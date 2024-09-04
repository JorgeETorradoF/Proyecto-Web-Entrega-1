package com.example.ProyectoWeb.test;

import com.example.ProyectoWeb.entrega1.dto.LoginDTO;
import com.example.ProyectoWeb.entrega1.dto.RespuestaLoginDTO;
import com.example.ProyectoWeb.entrega1.exception.CorreoNoExistenteException;
import com.example.ProyectoWeb.entrega1.service.ServicioLogin;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {com.example.ProyectoWeb.entrega1.TestApplication.class})
@AutoConfigureMockMvc
class ControladorLoginTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicioLogin servicioLogin;

    @Test
    @Order(1)
     void testLoginUsuario() throws Exception {
        RespuestaLoginDTO respuesta = new RespuestaLoginDTO(1, true);
        when(servicioLogin.loginUser(any(LoginDTO.class))).thenReturn(respuesta);

        mockMvc.perform(get("/iniciar-sesion/ja.vasquez@gmail.com/1234"))
                .andExpect(status().isOk())  
                .andExpect(content().string("/arrendador/1")); 
    }

    @Test
     void testLoginUsuarioCorreoNoexistente() throws Exception {
        when(servicioLogin.loginUser(any(LoginDTO.class)))
                .thenThrow(new CorreoNoExistenteException("Correo no existente"));

        mockMvc.perform(get("/iniciar-sesion/ja.vasquez@gmail.com/1234"))
                .andExpect(status().isBadRequest()); // Verifica el estado 400 Bad Request
    }

    @Test
     void testLoginUsuario_InternalServerError() throws Exception {
        when(servicioLogin.loginUser(any(LoginDTO.class)))
                .thenThrow(new RuntimeException("Error inesperado"));

        mockMvc.perform(get("/iniciar-sesion/ja.vasquez@gmail.com/1234"))
                .andExpect(status().isInternalServerError()); // Verifica el estado 500 Internal Server Error
    }
}