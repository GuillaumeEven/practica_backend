# ResponseEntity

`ResponseEntity<T>` représente une réponse HTTP complète : **status code**, **headers** et **body**.  
C'est le type de retour à utiliser dans un controller quand on veut contrôler précisément ce qu'on renvoie au client.

---

## Méthodes de base

### 1. `ResponseEntity.ok(body)`
200 OK avec un body.
```java
ResponseEntity.ok(usuario); // 200 + body
```

---

### 2. `ResponseEntity.status(HttpStatus).body(body)`
Choisir le status code manuellement.
```java
ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario); // 201 + body
ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado"); // 404 + body
```

---

### 3. `ResponseEntity.noContent()`
204 No Content — réponse sans body (ex: DELETE réussi).
```java
ResponseEntity.noContent().build(); // 204, pas de body
```

---

### 4. `ResponseEntity.badRequest()`
400 Bad Request — raccourci pour les erreurs de validation.
```java
ResponseEntity.badRequest().body("El campo nombre es obligatorio"); // 400 + message
```

---

## Exemple concret dans un controller

```java
@GetMapping("/{id}")
public ResponseEntity<UsuarioGetDTO> getUsuario(@PathVariable Long id) {
    Optional<UsuarioGetDTO> usuario = usuarioService.findById(id);

    if (usuario.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    return ResponseEntity.ok(usuario.get());
}
```

---

## Résumé rapide

| Méthode                          | Status | Body      |
|----------------------------------|--------|-----------|
| `ResponseEntity.ok(body)`        | 200    | oui       |
| `ResponseEntity.status(...)`     | libre  | optionnel |
| `ResponseEntity.noContent()`     | 204    | non       |
| `ResponseEntity.badRequest()`    | 400    | optionnel |

---

## ResponseEntity type par méthode HTTP

| Méthode HTTP | Status typique | Type de retour recommandé                          | Exemple                                                        |
|--------------|----------------|----------------------------------------------------|----------------------------------------------------------------|
| `GET`        | 200 / 404      | `ResponseEntity<MonDTO>`                           | `ResponseEntity<UsuarioGetDTO>`                                |
| `POST`       | 201            | `ResponseEntity<MonDTO>`                           | `ResponseEntity<UsuarioGetDTO>`                                |
| `PUT`        | 200 / 404      | `ResponseEntity<MonDTO>`                           | `ResponseEntity<UsuarioGetDTO>`                                |
| `PATCH`      | 200 / 404      | `ResponseEntity<MonDTO>`                           | `ResponseEntity<UsuarioGetDTO>`                                |
| `DELETE`     | 204            | `ResponseEntity<Void>`                             | `ResponseEntity<Void>`                                         |

### Exemples

```java
// GET — retourne une ressource ou 404
@GetMapping("/{id}")
public ResponseEntity<UsuarioGetDTO> get(@PathVariable Long id) {
    return usuarioService.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
}

// POST — crée une ressource, répond 201
@PostMapping
public ResponseEntity<UsuarioGetDTO> create(@RequestBody UsuarioPostDTO dto) {
    UsuarioGetDTO created = usuarioService.save(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
}

// PUT — met à jour, répond 200 ou 404
@PutMapping("/{id}")
public ResponseEntity<UsuarioGetDTO> update(@PathVariable Long id, @RequestBody UsuarioPostDTO dto) {
    return usuarioService.update(id, dto)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
}

// DELETE — supprime, pas de body
@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
    usuarioService.delete(id);
    return ResponseEntity.noContent().build();
}
```
