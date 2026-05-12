# Guia de uso de excepciones globales

## Objetivo
Este documento explica como funciona el manejo global de excepciones del proyecto y como usar las excepciones del paquete `exception` para devolver errores HTTP consistentes.

## Componentes actuales

### 1) GlobalControllerExceptionHandler
Ubicacion: `src/main/java/es/ediae/master/programacion/gestionusuario/controller/handlers/GlobalControllerExceptionHandler.java`

Actualmente define estos handlers:

- `@ExceptionHandler(UnsupportedOperationException.class)`
  - Devuelve `501 NOT_IMPLEMENTED`
  - Cuerpo: `String` con `ex.getMessage()`

- `@ExceptionHandler(GeneralException.class)`
  - Devuelve `501 NOT_IMPLEMENTED`
  - Cuerpo: `ModelMap` con:
    - `error`: nombre de la clase de excepcion (por ejemplo `RepositoryMethodNotImplementedException`)
    - `errorCode`: codigo funcional
    - `errorMessage`: mensaje funcional

### 2) Jerarquia de excepciones del paquete exception
Ubicacion: `src/main/java/es/ediae/master/programacion/gestionusuario/exception`

- `GeneralException extends Exception`
  - Campos:
    - `codigoDeError` (int)
    - `mensajeDeError` (String)
  - Constructor:
    - `GeneralException(int codigoDeError, String mensajeDeError)`

- `UsuarioNoValidoException extends GeneralException`
  - Usa:
    - `GeneralConstant.USUARIO_NO_VALIDO_ERROR_CODE`
    - `GeneralConstant.USUARIO_NO_VALIDO_ERROR_MESSAGE`

- `RepositoryMethodNotImplementedException extends GeneralException`
  - Usa:
    - `GeneralConstant.REPOSITORY_METHOD_NOT_IMPLEMENTED_ERROR_CODE`
    - `GeneralConstant.REPOSITORY_METHOD_NOT_IMPLEMENTED_ERROR_MESSAGE`

## Flujo recomendado de uso

1. Detectar en servicio/controlador una condicion de negocio invalida.
2. Lanzar una excepcion de dominio que extienda `GeneralException`.
3. No capturarla en el propio controlador salvo que quieras traducir una excepcion tecnica.
4. Dejar que `GlobalControllerExceptionHandler` construya la respuesta HTTP.

## Ejemplos con las excepciones existentes

### A) Usuario no valido
Uso cuando falle una validacion de credenciales o de formato de usuario.

```java
if (!credencialesValidas) {
    throw new UsuarioNoValidoException();
}
```

Respuesta final (via handler de `GeneralException`):

- HTTP: `501 NOT_IMPLEMENTED`
- Body JSON equivalente:

```json
{
  "error": "UsuarioNoValidoException",
  "errorCode": 3,
  "errorMessage": "El usuario y/o contrasena no son validos"
}
```

### B) Metodo de repositorio no implementado
Uso cuando un endpoint depende de una operacion no soportada por el repositorio.

Ejemplo real en `DireccionController`:

```java
@GetMapping("/direcciones/{id}")
public DireccionEntity obtenerDireccionPorId(@PathVariable Integer id) throws GeneralException {
    try {
        return direccionService.obtenerDireccionById(id);
    } catch (UnsupportedOperationException ex) {
        throw new RepositoryMethodNotImplementedException();
    }
}
```

Respuesta final:

- HTTP: `501 NOT_IMPLEMENTED`
- Body JSON equivalente:

```json
{
  "error": "RepositoryMethodNotImplementedException",
  "errorCode": 11,
  "errorMessage": "El metodo del repositorio no ha sido implementado"
}
```

## Como crear nuevas excepciones de negocio

Para mantener consistencia con el proyecto:

1. Define codigo y mensaje en `GeneralConstant`.
2. Crea una clase en el paquete `exception` que extienda `GeneralException`.
3. Lanza esa excepcion desde servicio/controlador cuando corresponda.
4. No necesitas tocar `GlobalControllerExceptionHandler` si hereda de `GeneralException`.

Plantilla:

```java
public class NuevaReglaException extends GeneralException {

    public NuevaReglaException() {
        super(GeneralConstant.NUEVA_REGLA_ERROR_CODE, GeneralConstant.NUEVA_REGLA_ERROR_MESSAGE);
    }
}
```

## Nota sobre el nombre del paquete
En el proyecto el paquete es `exception`.
Si en documentacion interna aparece `exceptionp`, tratalo como un typo y usa `exception`.

## Recomendaciones de mejora (opcional)

- Actualmente todas las `GeneralException` responden con `501`.
  - Puedes evolucionar el handler para mapear por tipo de error a `400`, `401`, `403`, `404`, etc.
- Conviene unificar formato de error para todas las excepciones (incluida `UnsupportedOperationException`) usando el mismo objeto JSON.
