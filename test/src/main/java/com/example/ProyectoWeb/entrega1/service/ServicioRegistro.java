package com.example.ProyectoWeb.entrega1.service;

import com.example.ProyectoWeb.entrega1.dto.RegistroDTO;
import com.example.ProyectoWeb.entrega1.dto.UsuarioRegistroDTO;
import com.example.ProyectoWeb.entrega1.exception.CamposInvalidosException;
import com.example.ProyectoWeb.entrega1.exception.CorreoRegistradoException;
import com.example.ProyectoWeb.entrega1.model.Arrendadores;
import com.example.ProyectoWeb.entrega1.model.Arrendatarios;
import com.example.ProyectoWeb.entrega1.model.Usuario;
import com.example.ProyectoWeb.entrega1.repository.RepositorioArrendadores;
import com.example.ProyectoWeb.entrega1.repository.RepositorioArrendatarios;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

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

    //validamos que no estén vacíos los nombres y apellidos
    public boolean nombresApellidosValidos(String nombre, String apellido)
    {
        return !nombre.isEmpty() && !apellido.isEmpty();
    }

    public boolean contraseñaValida(String contraseña)
    {
        //luego validamos lo de contraseña segura con x digitos y mezclando simbolos y tal
        return !contraseña.isEmpty();
    }
    //validamos correo con regex
    public boolean emailValido(String email)
    {
        String EMAIL_PATTERN = 
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (email == null || email.isEmpty()) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public Usuario registerUser(RegistroDTO registroDTO) throws CorreoRegistradoException, CamposInvalidosException {
        Usuario savedUser = new Usuario();
        //si todos los campos están llenos y son válidos se prosigue con el registro
        if(nombresApellidosValidos(registroDTO.getNombre(),registroDTO.getApellido()) && contraseñaValida(registroDTO.getContraseña()) && emailValido(registroDTO.getCorreo()))
        {
            //instanciamos el DTO a mapear
            UsuarioRegistroDTO usuarioDTO = new UsuarioRegistroDTO(
            registroDTO.getNombre(), 
            registroDTO.getApellido(), 
            registroDTO.getCorreo(),
            registroDTO.getContraseña()
            );

            //revisamos que el correo no haya sido ya registrado
            boolean existeCorreo = repositorioArrendadores.existsByCorreo(registroDTO.getCorreo()) || repositorioArrendatarios.existsByCorreo(registroDTO.getCorreo());

            //dependiendo de si hay correo registrado o no y que tipo de usuario será se realiza el registro
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
        }
        //de otro modo se deja morir el asunto
        else
        {
            //mensaje de error llene todos los campos
            throw new CamposInvalidosException("Por favor llene todos los campos e ingrese un correo valido");
        }
        return savedUser;
    }
}
