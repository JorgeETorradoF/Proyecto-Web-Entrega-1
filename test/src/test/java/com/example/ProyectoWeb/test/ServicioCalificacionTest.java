package com.example.ProyectoWeb.test;

import com.example.ProyectoWeb.entrega1.model.Arrendadores;
import com.example.ProyectoWeb.entrega1.model.Arrendatarios;
import com.example.ProyectoWeb.entrega1.repository.RepositorioArrendadores;
import com.example.ProyectoWeb.entrega1.repository.RepositorioArrendatarios;
import com.example.ProyectoWeb.entrega1.service.ServicioCalificacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ServicioCalificacionTest {

    @Mock
    private RepositorioArrendadores repositorioArrendadores;

    @Mock
    private RepositorioArrendatarios repositorioArrendatarios;

    @InjectMocks
    private ServicioCalificacion servicioCalificacion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calificarArrendador() {
        Arrendadores arrendador = new Arrendadores();
        arrendador.setId(1);
        arrendador.setPromedio(4.0f);
        arrendador.setCantiCalif(2);

        when(repositorioArrendadores.findById(1)).thenReturn(java.util.Optional.of(arrendador));

        servicioCalificacion.calificarArrendador(1, 5.0);

        verify(repositorioArrendadores).save(arrendador);
    }

    @Test
    void calificarArrendatario() {
    Arrendatarios arrendatario = new Arrendatarios();
    arrendatario.setId(1);
    arrendatario.setPromedio(4.0f);
    arrendatario.setCantiCalif(2);

    when(repositorioArrendatarios.findById(1)).thenReturn(java.util.Optional.of(arrendatario));

    servicioCalificacion.calificarArrendatario(Integer.valueOf(1), Float.valueOf(5.0f));

    verify(repositorioArrendatarios).save(arrendatario);
}

}
