
    create table usuarios (
        id bigint not null auto_increment,
        nick_usuario varchar(255) not null,
        contrasena varchar(255) not null,
        nombre varchar(255) not null,
        primer_apellido varchar(255) not null,
        segundo_apellido varchar(255),
        fecha_nacimiento date not null,
        hora_desayuno time(6),
        fecha_hora_creacion datetime(6) not null,
        primary key (id)
    ) engine=InnoDB;