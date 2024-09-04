package com.example.ProyectoWeb.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.ProyectoWeb.entrega1.prueba.controllers.ControladorGeneral;

@WebMvcTest(ControladorGeneral.class)
public class ControladorGeneralTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testInicio() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/inicio"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("inicio"));
    }

    @Test
    public void testRegistro() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/registro"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("registro"));
    }

    @Test
    public void testIniciarSesion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/iniciar-sesion"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("iniciar-sesion"));
    }
}
