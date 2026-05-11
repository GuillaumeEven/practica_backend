# Pruebas en Spring Boot: Mockito y DataJpaTest

Esta guía explica cómo escribir pruebas para aplicaciones Spring Boot, tomando como referencia dos ejemplos reales del proyecto.

---

## Idea principal: dos capas, dos tipos de prueba

En una aplicación Spring Boot típica hay dos capas que necesitan pruebas distintas:

| Capa | Tipo de prueba | Qué comprueba |
|---|---|---|
| Servicio | Mockito | Lógica de negocio, sin base de datos |
| Repositorio | DataJpaTest | Consultas JPA reales con base de datos |

Mantener esta separación es importante: las pruebas de servicio son rápidas y aisladas, mientras que las de repositorio son más lentas pero verifican que el acceso a datos funciona de verdad.

---

## Pruebas de servicio con Mockito

### ¿Qué es Mockito?

Mockito es una librería que crea **dobles de prueba** (llamados *mocks*) de tus dependencias. En lugar de hablar con la base de datos real, el servicio habla con un "impostor" que tú controlas. Así puedes comprobar la lógica del servicio sin preocuparte por la infraestructura.

### Anotaciones imprescindibles

| Anotación | Dónde va | Para qué sirve |
|---|---|---|
| `@ExtendWith(MockitoExtension.class)` | En la clase | Activa Mockito con JUnit 5 |
| `@Mock` | En un campo | Crea un doble de prueba de esa dependencia |
| `@InjectMocks` | En un campo | Crea el objeto a probar e inyecta los mocks automáticamente |
| `@BeforeEach` | En un método | Se ejecuta antes de cada test para preparar datos |

### Estructura recomendada: Arrange – Act – Assert

Cada test debe seguir tres pasos:

1. **Arrange** (preparar): define qué debe devolver el mock y crea los datos de prueba.
2. **Act** (actuar): llama al método del servicio que quieres probar.
3. **Assert** (verificar): comprueba que el resultado es el esperado.

### Ejemplo completo

```java
package es.ediae.master.programacion.gestionusuario.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;
import es.ediae.master.programacion.gestionusuario.repository.DireccionRepository;
import es.ediae.master.programacion.gestionusuario.service.impl.DireccionService;

@ExtendWith(MockitoExtension.class)                  // (1) activa Mockito
class DireccionServiceTest {

    @Mock
    private DireccionRepository mockDireccionRepository;  // (2) doble del repositorio

    @InjectMocks
    private DireccionService direccionService;             // (3) servicio real con mock inyectado

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
        d1.setNumeroCalle(10);
        d1.setDireccionPrincipal(true);
        d1.setUsuario(usuario);

        d2 = new DireccionEntity();
        d2.setId(2);
        d2.setNombreCalle("Avenida del Sol");
        d2.setNumeroCalle(5);
        d2.setDireccionPrincipal(false);
        d2.setUsuario(usuario);
    }

    @Test
    void testObtenerAllDirecciones_devuelveLista() {
        // Arrange: el mock devuelve dos direcciones
        when(mockDireccionRepository.findAll()).thenReturn(List.of(d1, d2));

        // Act: se llama al método del servicio
        List<DireccionEntity> resultado = direccionService.obtenerAllDirecciones();

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
        List<DireccionEntity> resultado = direccionService.obtenerAllDirecciones();

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(mockDireccionRepository).findAll();
    }
}
```

### Qué se aprende en este ejemplo

- **`when(...).thenReturn(...)`**: le dice al mock qué debe devolver cuando se llame a un método. Es el corazón de Mockito.
- **`verify(...)`**: comprueba que el mock fue llamado (y cuántas veces). Muy útil para validar que el servicio sí delega en el repositorio.
- **Dos casos de prueba**: lista con datos y lista vacía. Siempre es buena práctica cubrir el caso normal y el caso extremo.
- **Nombres descriptivos**: `testObtenerAllDirecciones_devuelveLista` explica exactamente qué prueba ese test. Así, si falla, sabes dónde mirar.

---

## Pruebas de repositorio con DataJpaTest

### ¿Qué es DataJpaTest?

`@DataJpaTest` es una anotación de Spring Boot que arranca solo la parte de persistencia de la aplicación (entidades JPA y repositorios), sin levantar el servidor web ni los servicios. Esto hace que los tests sean más rápidos que un test de integración completo, pero sí ejecutan consultas reales contra una base de datos.

### Anotaciones imprescindibles

| Anotación | Dónde va | Para qué sirve |
|---|---|---|
| `@DataJpaTest` | En la clase | Carga solo el contexto JPA (entidades y repositorios) |
| `@AutoConfigureTestDatabase(replace = NONE)` | En la clase | Usa la base de datos real configurada, en lugar de sustituirla por una embebida (H2) |
| `@Autowired` | En un campo | Inyecta el repositorio real (no un mock) |

### Datos de prueba

A diferencia de Mockito, aquí se trabaja con una base de datos real. Los datos de prueba pueden venir de:

- **Scripts de migración Flyway** (como en este proyecto): los archivos `V1__...sql` y `V2__...sql` crean las tablas y rellenan datos antes de que se ejecuten los tests.
- Scripts SQL específicos para tests (con `@Sql`), si quieres datos distintos de los de producción.

En el ejemplo de abajo, el test asume que en la base de datos ya existen dos direcciones para el usuario con id 1, insertadas por `V2__rellenar_esquema_inicial.sql`.

### Ejemplo completo

```java
package es.ediae.master.programacion.gestionusuario.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;

@DataJpaTest                                                        // (1) solo contexto JPA
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // (2) base de datos real
class DireccionRepositoryTest {

    @Autowired
    private DireccionRepository direccionRepository;                // (3) repositorio real

    @Test
    void buscarPorUsuarioId_devuelveSoloDireccionesDelUsuarioIndicado() {
        // Datos de prueba existentes en V2__rellenar_esquema_inicial.sql
        Integer usuarioIdExistente = 1;

        List<DireccionEntity> resultado = direccionRepository.buscarPorUsuarioId(usuarioIdExistente);

        assertThat(resultado).hasSize(2);                           // exactamente dos resultados
        assertThat(resultado)
            .extracting(DireccionEntity::getNombreCalle)
            .containsExactlyInAnyOrder("Calle Mayor", "Calle Menor");  // los nombres esperados
        assertThat(resultado)
            .allMatch(direccion -> direccion.getUsuario().getId().equals(usuarioIdExistente)); // todos del mismo usuario
    }
}
```

### Qué se aprende en este ejemplo

- **AssertJ (`assertThat`)**: librería de aserciones fluida que Spring Boot incluye por defecto. Su sintaxis en cadena hace que los tests sean muy legibles.
- **`extracting(...)`**: extrae un campo de todos los objetos de la lista para comprobarlo fácilmente, sin necesidad de iterar.
- **`containsExactlyInAnyOrder`**: verifica que la lista contiene exactamente esos elementos, en cualquier orden. Útil cuando el orden de las filas no está garantizado.
- **`allMatch`**: comprueba que todos los elementos de la lista cumplen una condición. Aquí se verifica que todas las direcciones devueltas pertenecen al usuario correcto.

---

## Cuándo usar cada tipo de prueba

| Situación | Usa |
|---|---|
| Quieres probar reglas de negocio del servicio | Mockito |
| Quieres que los tests sean muy rápidos | Mockito |
| Quieres probar una consulta JPA personalizada | DataJpaTest |
| Quieres detectar errores en el mapeo de entidades | DataJpaTest |
| Quieres probar que las relaciones entre entidades funcionan | DataJpaTest |

---

## Checklist para escribir un nuevo test

### Servicio (Mockito)

- [ ] Crear clase en el paquete `service` de test
- [ ] Añadir `@ExtendWith(MockitoExtension.class)`
- [ ] Declarar con `@Mock` cada dependencia del servicio
- [ ] Declarar con `@InjectMocks` el servicio a probar
- [ ] Preparar datos en `@BeforeEach`
- [ ] Escribir un test por caso de uso (caso normal + caso vacío como mínimo)
- [ ] Seguir la estructura Arrange – Act – Assert
- [ ] Añadir `verify(...)` para confirmar interacciones con el mock

### Repositorio (DataJpaTest)

- [ ] Crear clase en el paquete `repository` de test
- [ ] Añadir `@DataJpaTest`
- [ ] Añadir `@AutoConfigureTestDatabase(replace = NONE)` si usas base de datos real
- [ ] Inyectar el repositorio con `@Autowired`
- [ ] Asegurarse de que los datos de prueba existen (scripts SQL, Flyway, etc.)
- [ ] Verificar tamaño de la lista devuelta
- [ ] Verificar contenido concreto de los resultados
- [ ] Verificar coherencia (todos los resultados pertenecen a la entidad consultada)
