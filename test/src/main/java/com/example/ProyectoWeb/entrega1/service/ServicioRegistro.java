package com.example.ProyectoWeb.entrega1.service;

import com.example.ProyectoWeb.entrega1.dto.RegistroDTO;
import com.example.ProyectoWeb.entrega1.dto.UsuarioRegistroDTO;
import com.example.ProyectoWeb.entrega1.exception.CorreoRegistradoException;
import com.example.ProyectoWeb.entrega1.model.Arrendadores;
import com.example.ProyectoWeb.entrega1.model.Arrendatarios;
import com.example.ProyectoWeb.entrega1.model.Usuario;
import com.example.ProyectoWeb.entrega1.repository.RepositorioArrendadores;
import com.example.ProyectoWeb.entrega1.repository.RepositorioArrendatarios;

import java.util.List;

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

    public Usuario registerUser(RegistroDTO registroDTO) throws CorreoRegistradoException {
        UsuarioRegistroDTO usuarioDTO = new UsuarioRegistroDTO(
            registroDTO.getNombre(), 
            registroDTO.getApellido(), 
            registroDTO.getCorreo(),
            registroDTO.getContraseña()
        );

        Usuario savedUser;

        boolean existeCorreo = repositorioArrendadores.existsByCorreo(registroDTO.getCorreo()) || repositorioArrendatarios.existsByCorreo(registroDTO.getCorreo());

        if (registroDTO.isArrendador() && !existeCorreo) {
            Arrendadores arrendador = modelMapper.map(usuarioDTO, Arrendadores.class);
            savedUser = repositorioArrendadores.save(arrendador);
        } else if(!registroDTO.isArrendador() && !existeCorreo) {
            Arrendatarios arrendatario = modelMapper.map(usuarioDTO, Arrendatarios.class);
            savedUser = repositorioArrendatarios.save(arrendatario);
        }
        else {
            //mensaje de error correo ya existe
            throw new CorreoRegistradoException("Correo ya registrado");
        }
        return savedUser;
    }
}
