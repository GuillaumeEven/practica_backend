package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;
import es.ediae.master.programacion.gestionusuario.repository.IDireccionRepository;
import es.ediae.master.programacion.gestionusuario.service.impl.DireccionServiceImpl;

@ExtendWith(MockitoExtension.class)                  // (1) activa Mockito
class DireccionServiceTest {

    @Mock
    private IDireccionRepository mockDireccionRepository;  // (2) doble del repositorio

    @InjectMocks
    private DireccionServiceImpl direccionService;             // (3) servicio real con mock inyectado

    private UsuarioEntity usuario;
    private DireccionEntity d1;
    private DireccionEntity d2;

    @BeforeEach                                            // (4) se ejecuta antes de cada test
    void setUp() {
        usuario = new UsuarioEntity();
        usuario.setId(1);

        d1 = new DireccionEntity();
        d1.setId(1);
        d1.setNombreCalle("Calle Mayor");
        d1.setNumeroCalle("10");
        d1.setDireccionPrincipal(true);
        d1.setUsuario(usuario);

        d2 = new DireccionEntity();
        d2.setId(2);
        d2.setNombreCalle("Avenida del Sol");
        d2.setNumeroCalle("5");
        d2.setDireccionPrincipal(false);
        d2.setUsuario(usuario);
    }

    @Test
    void testObtenerAllDirecciones_devuelveLista() {
        // Arrange: el mock devuelve dos direcciones
        when(mockDireccionRepository.findAll()).thenReturn(List.of(d1, d2));

        // Act: se llama al método del servicio
        List<DireccionModel> resultado = direccionService.obtenerAllDirecciones();

        // Assert: se comprueba el resultado
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Calle Mayor", resultado.get(0).getNombreCalle());
        verify(mockDireccionRepository, times(1)).findAll();  // se verificó que findAll se llamó una vez
    }

    @Test
    void testObtenerAllDirecciones_listaVacia() {
        // Arrange: el mock devuelve lista vacía
        when(mockDireccionRepository.findAll()).thenReturn(List.of());

        // Act
        List<DireccionModel> resultado = direccionService.obtenerAllDirecciones();

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(mockDireccionRepository).findAll();
    }
}