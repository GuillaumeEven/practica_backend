# Notas del proyecto — Gestión de Usuarios

## Preguntas y respuestas

### Dirección principal única

**Problema:** ¿Qué hacer si el usuario intenta indicar una segunda dirección como principal?

**Decisión:** El front Angular usará un radio button, sin posibilidad de marcar dos opciones. Si se selecciona una nueva dirección principal, reemplaza a la anterior automáticamente.

---

## Entidades y relaciones

### Diagrama de relaciones

```
usuario ──────< direccion         (1 a 1..*)   @ManyToOne en DireccionEntity
usuario >────── genero            (* a 1)      @ManyToOne en UsuarioEntity
usuario >────── puesto_de_trabajo (* a 0..1)   @ManyToOne en UsuarioEntity
```

### usuario

| Campo | Tipo | Nullable |
|---|---|---|
| `id` | Integer | ❌ |
| `nick_usuario` | String | ❌ |
| `contrasena` | String | ❌ |
| `fecha_hora_creacion` | datetime | ❌ |
| `genero` | Genero | ❌ |
| `nombre` | String | ❌ |
| `primer_apellido` | String | ❌ |
| `segundo_apellido` | String | ✅ |
| `fecha_nacimiento` | date | ❌ |
| `hora_desayuno` | time | ✅ |
| `puesto_trabajo` | PuestoDeTrabajo | ✅ |

### genero

| Campo | Tipo |
|---|---|
| `id` | Integer |
| `nombre` | String |

### puesto_de_trabajo

| Campo | Tipo |
|---|---|
| `id` | Integer |
| `nombre` | String |

### direccion

| Campo | Tipo | Nullable |
|---|---|---|
| `id` | Integer | ❌ |
| `nombre_calle` | String | ❌ |
| `numero_calle` | Integer | ✅ |
| `usuario` | Usuario | ❌ |
| `direccion_principal` | Boolean | ❌ — solo una por usuario |

---

## Recursos de desarrollo

### Spring — Anotaciones útiles

| Anotación | Para qué sirve |
|---|---|
| `@PathVariable` | Extrae `{id}` de `/api/users/{id}` |
| `@RequestParam` | Extrae `?id=1` de la URL |
| `@RequestBody` | Deserializa el body JSON |
| `@ResponseStatus` | Fuerza un código HTTP de respuesta |
| `@Valid` | Activa la validación del DTO (Bean Validation) |
| `@CrossOrigin` | Permite llamadas desde otros dominios (CORS) — se abre a `"*"` a nivel de clase, luego se gestiona por método |

### ResponseEntity — ejemplos

```java
// 404 con detalle
return ResponseEntity
    .status(HttpStatus.NOT_FOUND)
    .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

// 404 sin body
return ResponseEntity.notFound().build();

// 201 Created con el objeto nuevo
return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuarioDTO);
```

### Estructura de respuesta API recomendada

```json
{
  "data": { ... },
  "error": "",
  "meta": {
    "sizeof": 20,
    "page": 1,
    "total": 150
  }
}
```

> Para `GET by id`: `sizeof` y `page` son `null`.

### DTOs — usar `record`

Los DTOs deben ser `record` inmutables. Un `record` en Java:
- Campos `final`, declarados en la cabecera
- Constructor, getters (`id()`, `nombre()`...), `equals`, `hashCode`, `toString` — generados automáticamente
- Sin setters

```java
public record UsuarioGetDTO(
    Integer id,
    String nickUsuario,
    LocalDateTime fechaHoraCreacion,
    String nombre,
    String primerApellido,
    String segundoApellido,
    LocalDate fechaNacimiento,
    LocalTime horaDesayuno
    // ⚠️ NO incluir la contraseña
) {}
```

> Si Jackson no resuelve los nombres de parámetros → añadir `@JsonProperty` o compilar con `-parameters`.

> Ver también: [arquitectura_capas.md](arquitectura_capas.md) para el flujo completo Controller → Service → Repository y las buenas prácticas de mapping Entity/Model/DTO.

### Hibernate — `@ManyToOne` / `@OneToMany`

`@ManyToOne` en una entidad es el espejo de `@OneToMany` en la otra.

Referencia: https://www.baeldung.com/hibernate-one-to-many

### Hibernate + Flyway — problema de cohabitación

> [!WARNING]
> Ver application.yml por la solución con opciones de generación de scripts

### Proyectos grandes — MapStruct

Para mappings Entity ↔ DTO a gran escala, usar **MapStruct** (Spring).

---

## TODO

- [ ] Pasar todos los DTOs a `record`
- [ ] Impedir duplicación de género
- [ ] Impedir duplicación de nombre
- [ ] refactor: [controllers = [dtos] + controllers, services = [models] + services, repository = [entities] + repos ?]
- [ ] Añadir endpoints de error globales (404, 403...)
- [ ] Cambiar tipo de `id` de `Integer` a `Integer`
- [ ] crear un unico script para la db
- [ ] validar en la capa controllers/dtos:
  - [ ] nick_usuario not null
  - [ ] contrasena not null
  - [ ] fecha nacimiento not null not empty
  - [ ] lo que es nullable validar con notempty
- [ ] Pasar los repo como sql nativo (@NativeSQL)
- [ ] Y eso ? "Marque la méthode de création @Transactional pour que les proxies et les sets d’association fonctionnent correctement.
"

## Ejercicio 2

iniciarSesion: permite a un usuario iniciar sesión en la aplicación con un par válido
de nombre de usuario / contraseña.
Nota: esta aplicación no incluye seguridad, por lo tanto, el método puede devolver
simplemente un boolean (true/false) o lo que consideres.
- [x] obtenerUsuarios: lista todos los usuarios de la aplicación, incluyendo su género y
puesto de trabajo (id y nombre).
- [x] obtenerUsuario: obtiene un usuario por su id.
- [x] crearUsuario: crea un nuevo usuario.
- [x] actualizarUsuario: actualiza un usuario existente.
- [x] eliminarUsuario: elimina un usuario.
- [x] obtenerDirecciones: lista todas las direcciones de un usuario específico.
- [x] obtenerDireccion: obtiene una dirección por id.
- [x] crearDireccion: crea una dirección para un usuario.
- [x] actualizarDireccion: actualiza una dirección.
- [x] eliminarDireccion: elimina una dirección.
- [x] obtenerGeneros: lista todos los géneros disponibles (puede estar en el controlador
de usuario, pero debe tener su propio servicio).
- [ ] obtenerPuestosDeTrabajo: lista todos los puestos de trabajo disponibles (puede
estar en el controlador de usuario, pero debe tener su propio servicio).
- [ ] fixes:
  - [ ] harmonisar las respuestas en responseEntity
  - [ ] quedan snake case en las entities
- [ ] usuarioController.actualizar(): es posible cambiar por un PostUsuarioDTO ?

Front repo: https://github.com/Kalebros/practica_frontend