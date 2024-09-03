package com.example.tallerthymeleaf.test;

import com.example.ProyectoWeb.entrega1.dto.LoginDTO;
import com.example.ProyectoWeb.entrega1.dto.RegistroDTO;
import com.example.ProyectoWeb.entrega1.dto.RespuestaLoginDTO;
import com.example.ProyectoWeb.entrega1.exception.CorreoNoExistenteException;
import com.example.ProyectoWeb.entrega1.model.Usuario;
import com.example.ProyectoWeb.entrega1.service.ServicioLogin;
import com.example.ProyectoWeb.entrega1.service.ServicioRegistro;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {com.example.ProyectoWeb.entrega1.TestApplication.class})
public class LoginTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicioRegistro servicioRegistro;
    private ServicioLogin servicioLogin;

    @Test
    @Order(1)
    public void testLoginUsuario() throws Exception {
        
        RespuestaLoginDTO respuesta = new RespuestaLoginDTO(1, true);
        Mockito.when(servicioLogin.loginUser(Mockito.any(LoginDTO.class)))
               .thenReturn(respuesta);
            
        mockMvc.perform(get("/iniciar-sesion/ja.vasquez@gmail.com/1234"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML));
        
    }

    @Test
    public void testLoginUsuarioCorreoNoexistente() throws Exception {
        // Configurar excepción simulada
        Mockito.when(servicioLogin.loginUser(any(LoginDTO.class))).thenThrow(new CorreoNoExistenteException("Correo no existente"));

        // Realizar la solicitud y verificar la respuesta
        mockMvc.perform(get("/iniciar-sesion/ja.vasquez@gmail.com/1234"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.TEXT_HTML));
    }

    @Test
    public void testLoginUsuario_InternalServerError() throws Exception {
        // Configurar excepción simulada
        Mockito.when(servicioLogin.loginUser(any(LoginDTO.class))).thenThrow(new RuntimeException("Error inesperado"));

        // Realizar la solicitud y verificar la respuesta
        mockMvc.perform(get("/iniciar-sesion/ja.vasquez@gmail.com/1234"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.TEXT_HTML));
    }
}
