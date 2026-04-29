INSERT INTO generos (nombre) VALUES ('Hombre');
INSERT INTO generos (nombre) VALUES ('Mujer');
INSERT INTO generos (nombre) VALUES ('Otro');

INSERT INTO puestos_de_trabajo (nombre) VALUES ('Desarrollador');
INSERT INTO puestos_de_trabajo (nombre) VALUES ('Diseñador');
INSERT INTO puestos_de_trabajo (nombre) VALUES ('Tester');

INSERT INTO usuarios (fecha_nacimiento, genero_id, hora_desayuno, puesto_trabajo_id, fecha_hora_creacion, contrasena, nick_usuario, nombre, primer_apellido, segundo_apellido) VALUES ('1990-01-01', 1, '08:00:00', 1, '2024-06-01 10:00:00', 'contrasena123', 'usuario1', 'Juan', 'Pérez', 'García');

INSERT INTO usuarios (fecha_nacimiento, genero_id, hora_desayuno, puesto_trabajo_id, fecha_hora_creacion, contrasena, nick_usuario, nombre, primer_apellido, segundo_apellido) VALUES ('1992-02-15', 2, '08:30:00', 2, '2024-06-01 11:00:00', 'contrasena456', 'usuario2', 'María', 'López', 'Sánchez');

INSERT INTO direcciones (direccion_principal, usuario_id, nombre_calle, numero_calle) VALUES (1, 1, 'Calle Falsa', '123');