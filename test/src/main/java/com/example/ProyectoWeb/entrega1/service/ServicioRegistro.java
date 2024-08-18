package com.example.ProyectoWeb.entrega1.service;

import com.example.ProyectoWeb.entrega1.dto.RegistroDTO;
import com.example.ProyectoWeb.entrega1.dto.UsuarioRegistroDTO;
import com.example.ProyectoWeb.entrega1.model.Arrendador;
import com.example.ProyectoWeb.entrega1.model.Arrendatario;
import com.example.ProyectoWeb.entrega1.model.Usuario;
import com.example.ProyectoWeb.entrega1.repository.RepositorioArrendadores;
import com.example.ProyectoWeb.entrega1.repository.RepositorioArrendatarios;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioRegistro {

    @Autowired
    private RepositorioArrendadores repositorioArrendadores;

    @Autowired
    private RepositorioArrendatarios repositorioArrendatarios;

    @Autowired
    private ModelMapper modelMapper;

    public Usuario registerUser(RegistroDTO registroDTO) {
        UsuarioRegistroDTO usuarioDTO = new UsuarioRegistroDTO(
            registroDTO.getNombre(), 
            registroDTO.getApellido(), 
            registroDTO.getCorreo()
        );

        Usuario savedUser;

        if (registroDTO.isArrendador()) {
            Arrendador arrendador = modelMapper.map(usuarioDTO, Arrendador.class);
            savedUser = repositorioArrendadores.save(arrendador);
        } else {
            Arrendatario arrendatario = modelMapper.map(usuarioDTO, Arrendatario.class);
            savedUser = repositorioArrendatarios.save(arrendatario);
        }

        return savedUser;
    }
}
