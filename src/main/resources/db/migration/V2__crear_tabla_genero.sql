
    create table generos (
        id bigint not null auto_increment,
        nombre varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table usuarios
    -- crear la columna Genero_id
       add column genero_id bigint not null after fecha_nacimiento,
    -- crear la clave foranea
       add constraint fk_genero_usuarios
       foreign key (genero_id)
       references generos (id);