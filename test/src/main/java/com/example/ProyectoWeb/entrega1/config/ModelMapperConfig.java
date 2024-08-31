package com.example.ProyectoWeb.entrega1.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.ProyectoWeb.entrega1.dto.PropiedadDTO;
import com.example.ProyectoWeb.entrega1.model.Propiedades;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Configuración personalizada para ignorar el campo 'id'
        modelMapper.addMappings(new PropertyMap<PropiedadDTO, Propiedades>() {
            @Override
            protected void configure() {
                // Ignorar el campo 'id' en Propiedades cuando se mapea desde PropiedadDTO
                skip(destination.getId());
            }
        });

        return modelMapper;
    }
}
