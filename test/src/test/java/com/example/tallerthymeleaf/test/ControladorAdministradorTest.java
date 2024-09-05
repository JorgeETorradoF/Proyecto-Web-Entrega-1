import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.format.annotation.DateTimeFormat;

class ControladorAdministradorTest {

    @InjectMocks
    private ControladorAdministrador controladorAdministrador;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSolicitarArriendoFechaInicioInvalida() {
        // Configurar datos de prueba
        LocalDate fechaInicio = LocalDate.now().minusDays(1); // Fecha inicial inválida (anterior a la actual)
        LocalDate fechaFin = LocalDate.now().plusDays(2);
        int cantidadPersonas = 4;

        // Invocar el método de prueba
        String resultado = controladorAdministrador.solicitarArriendo(
                1, // ID del arrendatario
                1, // ID de la propiedad
                fechaInicio,
                fechaFin,
                cantidadPersonas,
                model
        );

        // Verificar el comportamiento esperado
        verify(model).addAttribute(eq("error"), eq("La fecha inicial no puede ser anterior a la fecha actual."));
        assertEquals("detalle-propiedad", resultado);
    }

    @Test
    public void testSolicitarArriendoFechaFinInvalida() {
        // Configurar datos de prueba
        LocalDate fechaInicio = LocalDate.now().plusDays(1);
        LocalDate fechaFin = LocalDate.now(); // Fecha final inválida (anterior a la inicial)
        int cantidadPersonas = 4;

        // Invocar el método de prueba
        String resultado = controladorAdministrador.solicitarArriendo(
                1,
                1,
                fechaInicio,
                fechaFin,
                cantidadPersonas,
                model
        );

        // Verificar el comportamiento esperado
        verify(model).addAttribute(eq("error"), eq("La fecha final debe ser al menos un día posterior a la fecha inicial."));
        assertEquals("detalle-propiedad", resultado);
    }

    @Test
    public void testSolicitarArriendoValido() {
        // Configurar datos de prueba
        LocalDate fechaInicio = LocalDate.now().plusDays(1);
        LocalDate fechaFin = LocalDate.now().plusDays(3);
        int cantidadPersonas = 4;

        // Invocar el método de prueba
        String resultado = controladorAdministrador.solicitarArriendo(
                1,
                1,
                fechaInicio,
                fechaFin,
                cantidadPersonas,
                model
        );

        // Verificar que redirija correctamente si los datos son válidos
        assertEquals("redirect:/arrendatario/1/solicitudes", resultado);
    }
}
