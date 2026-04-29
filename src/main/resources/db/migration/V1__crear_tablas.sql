    create table generos (
        id integer not null auto_increment,
        nombre varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table puestos_de_trabajo (
        id integer not null auto_increment,
        nombre varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table usuarios (
        fecha_nacimiento date not null,
        genero_id integer not null,
        hora_desayuno time(6),
        id integer not null auto_increment,
        puesto_trabajo_id integer,
        fecha_hora_creacion datetime(6) not null,
        contrasena varchar(255) not null,
        nick_usuario varchar(255) not null,
        nombre varchar(255) not null,
        primer_apellido varchar(255) not null,
        segundo_apellido varchar(255),
        primary key (id)
    ) engine=InnoDB;

        create table direcciones (
        direccion_principal bit not null,
        id integer not null auto_increment,
        usuario_id integer not null,
        nombre_calle varchar(255) not null,
        numero_calle varchar(255),
        primary key (id)
    ) engine=InnoDB;