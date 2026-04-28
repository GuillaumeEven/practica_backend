
    create table direcciones (
        direccion_principal bit not null,
        id integer not null auto_increment,
        usuario_id integer not null,
        nombre_calle varchar(255) not null,
        numero_calle varchar(255),
        primary key (id)
    ) engine=InnoDB;

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

    alter table generos 
       add constraint UK9u5buu0dehagjdoqm3wv6orm3 unique (nombre);

    alter table puestos_de_trabajo 
       add constraint UK6sxe4w1xjuam5p6iyg9ogrofj unique (nombre);

    alter table direcciones 
       add constraint FK54oy4k8b4ltgwmoq6kuocwhc7 
       foreign key (usuario_id) 
       references usuarios (id);

    alter table usuarios 
       add constraint FK7cbljtyx7p0dy7j7ckkcikb4v 
       foreign key (genero_id) 
       references generos (id);

    alter table usuarios 
       add constraint FK64ai2s5kd4dpdcxoruyqt8wxs 
       foreign key (puesto_trabajo_id) 
       references puestos_de_trabajo (id);
