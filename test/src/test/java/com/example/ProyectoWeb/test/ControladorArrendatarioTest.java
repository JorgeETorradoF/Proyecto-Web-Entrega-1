package com.example.ProyectoWeb.test;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = {com.example.ProyectoWeb.entrega1.TestApplication.class})
@AutoConfigureMockMvc 
class ControladorArrendatarioTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testInicio() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/arrendatario/123")) // Cambia el ID según sea necesario
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("inicio"));
    }

}
