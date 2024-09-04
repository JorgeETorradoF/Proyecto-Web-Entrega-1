package com.example.ProyectoWeb.test;

import com.example.ProyectoWeb.entrega1.dto.RegistroDTO;
import com.example.ProyectoWeb.entrega1.exception.CamposInvalidosException;
import com.example.ProyectoWeb.entrega1.exception.CorreoRegistradoException;
import com.example.ProyectoWeb.entrega1.model.Usuario;
import com.example.ProyectoWeb.entrega1.service.ServicioRegistro;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest(classes = {com.example.ProyectoWeb.entrega1.TestApplication.class})
@AutoConfigureMockMvc
class ControladorRegistroTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicioRegistro servicioRegistro;

    @Test
	@Order(1)
     void testRegistrarUsuarioArrendadorExitoso() throws Exception {

		Usuario Dio = new Usuario(1,"Dio","Brando","WRYYY123","konoDioDa@gmail.com",(float)0,0);
        Mockito.when(servicioRegistro.registerUser(Mockito.any(RegistroDTO.class)))
               .thenReturn(Dio);

		mockMvc.perform(post("/crear-cuenta")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content("{\"correo\":\"konoDioDa@gmail.com\",\"password\":\"WRYYY123\",\"tipoUsuario\":\"arrendador\"}"))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.correo", is("konoDioDa@gmail.com")));
   
    }

    @Test
     void testRegistrarUsuarioCorreoRegistrado() throws Exception {
        Mockito.when(servicioRegistro.registerUser(Mockito.any(RegistroDTO.class)))
               .thenThrow(new CorreoRegistradoException("El correo ya está registrado"));

        mockMvc.perform(post("/crear-cuenta")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"correo\":\"konoDioDa@gmail.com\",\"password\":\"WRYYY123\",\"tipoUsuario\":\"arrendador\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("El correo ya está registrado")));
    }

    @Test
     void testRegistrarUsuarioCamposInvalidos() throws Exception {
        Mockito.when(servicioRegistro.registerUser(Mockito.any(RegistroDTO.class)))
               .thenThrow(new CamposInvalidosException("Campos inválidos"));

        mockMvc.perform(post("/crear-cuenta")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"correo\":\"@example.com\",\"password\":\"password123\",\"tipoUsuario\":\"arrendador\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Campos inválidos")));
    }
}
