# Arquitectura en capas — Spring Boot REST

## Flujo HTTP global

```
┌──────────────────────────────────────────────────────────────────────────┐
│  Cliente HTTP  (Angular, Postman...)                                     │
│                           │  JSON (Request Body / Path Variable)         │
│                           ▼                                              │
│  ┌─────────────────────────────────────┐                                 │
│  │         CONTROLLER                  │  ← RequestDTO  (validación)     │
│  │  @RestController                    │  → ResponseDTO (respuesta API)  │
│  └───────────────┬─────────────────────┘                                 │
│                  │  llama al Service                                      │
│                  ▼                                                        │
│  ┌─────────────────────────────────────┐                                 │
│  │         SERVICE                     │  ← Model (lógica de negocio)    │
│  │  @Service / @Transactional          │  → reglas, validaciones         │
│  └───────────────┬─────────────────────┘                                 │
│                  │  llama al Repository                                   │
│                  ▼                                                        │
│  ┌─────────────────────────────────────┐                                 │
│  │         REPOSITORY                  │  ← Entity (JPA)                 │
│  │  JpaRepository<Entity, ID>          │  → lecturas/escrituras BDD      │
│  └───────────────┬─────────────────────┘                                 │
│                  │  SQL                                                   │
│                  ▼                                                        │
│              BASE DE DATOS                                                │
└──────────────────────────────────────────────────────────────────────────┘
```

---

## Rol de cada objeto

| Objeto | Capa | Función | Anotaciones JPA | Inmutable |
|---|---|---|---|---|
| `UsuarioEntity` | Repository | Representación directa de la tabla BDD | ✅ `@Entity`, `@Column`... | ❌ |
| `UsuarioModel` | Service | Lógica de negocio, transformaciones, validaciones | ❌ | opcional |
| `UsuarioGetDTO` | Controller | Respuesta API filtrada (sin contraseña, etc.) | ❌ | ✅ (`record`) |

> **Regla de oro**: nunca exponer una `Entity` directamente en la API.

---

## Flujo de mapping

### GET — lectura

```
BDD  →  Entity  →  Model  →  DTO  →  Cliente
       (repo)    (service) (controller)
```

```java
// Repository
List<UsuarioEntity> entities = usuarioRepository.findAll();

// Service : Entity → Model
List<UsuarioModel> models = entities.stream()
    .map(UsuarioModel::fromEntity)
    .toList();

// Controller : Model → DTO
List<UsuarioGetDTO> dtos = models.stream()
    .map(m -> new UsuarioGetDTO(m.getId(), m.getNombre(), ...))
    .toList();
```

> Si no hay lógica de negocio: saltarse el Model y mapear **Entity → DTO** directamente.

---

## DTO — usar un `record`

```java
public record UsuarioGetDTO(
    Integer id,
    String nickUsuario,
    LocalDateTime fechaHoraCreacion,
    String nombre,
    String primerApellido,
    String segundoApellido,    // puede ser null
    LocalDate fechaNacimiento,
    LocalTime horaDesayuno     // puede ser null
    // ⚠️ NO exponer la contraseña
) {}
```

| Característica | `record` Java |
|---|---|
| Campos | `final`, declarados en la cabecera |
| Constructor | generado automáticamente |
| Getters | `id()`, `nombre()`, etc. (no `getId()`) |
| Setters | ❌ ninguno — inmutable |
| `equals` / `hashCode` / `toString` | ✅ generados |

> Si Jackson no encuentra los nombres de parámetros → añadir `@JsonProperty` o compilar con `-parameters`.

---

## Anotaciones Spring útiles

| Anotación | Ubicación | Para qué sirve |
|---|---|---|
| `@PathVariable` | parámetro método | extrae `{id}` de `/api/users/{id}` |
| `@RequestParam` | parámetro método | extrae `?id=1` de la URL |
| `@RequestBody` | parámetro método | deserializa el body JSON |
| `@ResponseStatus` | método / clase | fuerza un código HTTP de respuesta |
| `@Valid` | parámetro método | activa la validación del DTO (Bean Validation) |
| `@CrossOrigin` | clase / método | permite llamadas desde otros dominios (CORS) |

---

## Estructura de respuesta API recomendada

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

> Para `GET by id`: `sizeof` y `page` pueden ser `null`.

---

## ResponseEntity — ejemplos

```java
// 404 con detalle
return ResponseEntity
    .status(HttpStatus.NOT_FOUND)
    .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

// 404 sin body
return ResponseEntity.notFound().build();

// 201 Created con el objeto creado
return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuarioDTO);
```

---

## Cuándo usar Model vs. DTO directo

```
¿Hay lógica de negocio?
        │
       SÍ ──→  Entity → Model → DTO
        │
        NO ──→ Entity → DTO  (servicio CRUD simple)
```

---

## TODOs pendientes

- [ ] Pasar los DTOs a `record`
- [ ] Impedir duplicación de género
