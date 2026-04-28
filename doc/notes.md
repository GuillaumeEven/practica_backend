
## Preguntas/respuestas:

1. Direccion principal unica

  Dos opciones:
    - levantar un error si el usuario intenta indicar una segunda direccion como principal;</br>
    - remplazar una por otra</br>
    Respuesta -> el front Angular sera en forma de radio buton, sin posibilidad de checkear dos opciones. Remplazaremos una por otra

## Entidades:

- usuario
  - id Integer
  - nick_usuario: String
  - contrasena: String
  - fecha_hora_creacion: datetime
  - genero: Genero
  - nombre: String
  - primer_apellido: String
  - segundo_apellido: String (NULLABLE)
  - fecha_nacimiento: date
  - hora_desayuno: time (NULLABLE)
  - puesto_trabajo: PuestoDeTrabajo (NULLABLE)
- genero
  - id: integer
  - nombre: string
- puesto_de_trabajo
  - id: integer
  - nombre: string
- direccion
  - id: Integer
  - nombre_calle: String
  - numero_calle: Integer (NULLABLE)
  - usuario: User
  - direccion_principal: Boolean → Solo puede haber una dirección principal por usuario.

usuario 1 - 1...* direccion  @ManyToOne</br>
usuario * - 1 genero @ManyToOne</br>
usuario * - 0...1 puesto_de_trabajo @ManyToOne</br>

## Recursos de desrrollo

### Conceptos

indices de distancia entre palabras

#### Hibernate

ManyToOne se refiere a un OneToMany en espejo </br>

[ver](https://www.baeldung.com/hibernate-one-to-many)

>[!WARNING]
> problemas de cohabitation hibernate/flyway:
>  Es importante
>  1. crear un clone en otra carpeta
>  2. crear otra db
>  3. En la nueava carpeta, cambiar url de la base por la copia
>  4. cambiar el yaml para desactivar flyway y activar hibernate
>  5. lanzar desde la nueva carpeta
>  6. hacer los cambios con hibernate
>  7. copiamos ddl desde workbench
>  8. pasar a la carpeta de origen
>  9. aplicar los dll en flyway


## Otras notas

Fecha de entrega: 25/05 ?



id
version 3.4.5

indices de distancia entre palabras
pour le 25/05 ?

@Valid = validation des donnees
Dto = filtrer ce qu'on veut exposer ou non

Grand projets: utilisation de MapStruct (Spring

Usar un record ??

RequestDto et ResponseDto -> en la capa de controllers
    - gestionan la validacion
    - on peut considerer qu'il faut un (deux) dto par endpoint ?

Reponse en cas de POST:
    - l'objet nouvellement créé
Exemple de reponse :
{
    "data": <objet>,
    "error": "",
    "meta":
        {
            "sizeof" (taille de page, null si get by id): int,
            "page": int,
            "total": int
            ...
        }
}

Recevoir/envoyer des fichiers: non pris en charge par Restfull

Ajouter des endpoints pour 404, 403...

@PathVariable (api/vi/users/2) -> 2
@RequestParam (user?id=1) -> 1
@RequestBody recup le body
@ResponseStatus
@CrossOrigin permet de recevoir les appels depuis d'autres domaines
Exemple: accepter tous les gets.
Dans l'usage, on ouvre à "*" en class controller, puis on gere dans les methods
El optiums ??

ResponseEntity<T>:
return ResponseEntity.status(404)
    .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Desk 42 not found"));

Return ResponseEntity.notFound().build()
