
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

## Otras notas

Fecha de entrega: 25/05 ?