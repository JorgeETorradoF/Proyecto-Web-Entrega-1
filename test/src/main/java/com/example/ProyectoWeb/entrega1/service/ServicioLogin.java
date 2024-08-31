package com.example.ProyectoWeb.entrega1.service;

import com.example.ProyectoWeb.entrega1.dto.LoginDTO;
import com.example.ProyectoWeb.entrega1.dto.RespuestaLoginDTO;
import com.example.ProyectoWeb.entrega1.exception.CorreoNoExistenteException;
import com.example.ProyectoWeb.entrega1.model.Arrendadores;
import com.example.ProyectoWeb.entrega1.model.Arrendatarios;
import com.example.ProyectoWeb.entrega1.repository.RepositorioArrendadores;
import com.example.ProyectoWeb.entrega1.repository.RepositorioArrendatarios;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioLogin {

    @Autowired
    private RepositorioArrendadores repositorioArrendadores;

    @Autowired
    private RepositorioArrendatarios repositorioArrendatarios;

    //validamos que la contraseña 
    public boolean contraseñaValida(String contraseña)
    {
        //por ahora solo se valida que no esté vacía, luego agregamos verificaciones de contraseña fuerte y todo eso
        return !contraseña.isEmpty();
    }

    //validamos correo con regex
    public boolean emailValido(String email)
    {
        //si el email es vacío retorno de una vez
        if (email == null || email.isEmpty()) {
            return false;
        }
        //regex para evaluar el email
        String EMAIL_PATTERN = 
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        //se evalúa el email luego de haber compilado la regex
        Matcher matcher = pattern.matcher(email);

        //retorno resultado
        return matcher.matches();
    }

    public RespuestaLoginDTO loginUser(LoginDTO loginDTO) throws CorreoNoExistenteException {
        RespuestaLoginDTO respuesta = new RespuestaLoginDTO(0, false);
        //si todos los campos están llenos y son válidos se prosigue con el registro
        if( contraseñaValida(loginDTO.getContraseña()) && emailValido(loginDTO.getCorreo()))
        {

            //revisamos que el correo exista y donde
            boolean existeCorreoArrendador = repositorioArrendadores.existsByCorreo(loginDTO.getCorreo());
            boolean existeCorreoArrendatario = repositorioArrendatarios.existsByCorreo(loginDTO.getCorreo());

            //dependiendo de si hay correo registrado o no y que tipo de usuario será se realiza el registro
            if (existeCorreoArrendador) {
                Arrendadores arrendador = repositorioArrendadores.findByCorreo(loginDTO.getCorreo());
                respuesta.setId(arrendador.getId());
                respuesta.setArrendador(true);
            } else if(existeCorreoArrendatario) {
                Arrendatarios arrendatario = repositorioArrendatarios.findByCorreo(loginDTO.getCorreo());
                respuesta.setId(arrendatario.getId());
                respuesta.setArrendador(false);
            }
            else {
                //mensaje de error correo no existente
                throw new CorreoNoExistenteException("Correo no existente");
            }
        }
        return respuesta;
    }
}
