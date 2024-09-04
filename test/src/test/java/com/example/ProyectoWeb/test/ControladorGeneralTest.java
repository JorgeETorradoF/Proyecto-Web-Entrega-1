package com.example.ProyectoWeb.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = {com.example.ProyectoWeb.entrega1.TestApplication.class})
@AutoConfigureMockMvc 
class ControladorGeneralTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testInicio() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/inicio"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("inicio"));
    }

    @Test
    void testRegistro() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/registro"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("registro"));
    }

    @Test
    void testIniciarSesion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/iniciar-sesion"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("iniciar-sesion"));
    }
}