package es.ediae.master.programacion.gestionusuario.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import es.ediae.master.programacion.gestionusuario.dtos.UsuarioGetDTO;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;

public class UsuarioModel {

    private Integer id;
    private String nick_usuario;
    private String contrasena;
    private LocalDateTime fecha_hora_creacion;
    private GeneroModel genero;
    private String nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private LocalDate fecha_nacimiento;
    private LocalTime hora_desayuno;
    private PuestoDeTrabajoModel puestoDeTrabajo;
    private List<DireccionModel> direcciones;

    public UsuarioModel(
            Integer id,
            String nick_usuario,
            String contrasena,
            LocalDateTime fecha_hora_creacion,
            GeneroModel genero,
            String nombre,
            String primer_apellido,
            String segundo_apellido,
            LocalDate fecha_nacimiento,
            LocalTime hora_desayuno,
            PuestoDeTrabajoModel puestoDeTrabajo,
            List<DireccionModel> direcciones
        ) {
        this.id = id;
        this.nick_usuario = nick_usuario;
        this.contrasena = contrasena;
        this.fecha_hora_creacion = fecha_hora_creacion;
        this.genero = genero;
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.hora_desayuno = hora_desayuno;
        this.puestoDeTrabajo = puestoDeTrabajo;
        this.direcciones = direcciones;
    }

    public GeneroModel getGenero() {
        return genero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick_usuario() {
        return nick_usuario;
    }

    public void setNick_usuario(String nick_usuario) {
        this.nick_usuario = nick_usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public LocalDateTime getFecha_hora_creacion() {
        return fecha_hora_creacion;
    }

    public GeneroModel getGeneroModel() {
        return genero;
    }

    public void setGenero(GeneroModel genero) {
        this.genero = genero;
    }

    public void setFecha_hora_creacion(LocalDateTime fecha_hora_creacion) {
        this.fecha_hora_creacion = fecha_hora_creacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public LocalTime getHora_desayuno() {
        return hora_desayuno;
    }

    public void setHora_desayuno(LocalTime hora_desayuno) {
        this.hora_desayuno = hora_desayuno;
    }

    public PuestoDeTrabajoModel getPuestoDeTrabajo() {
        return puestoDeTrabajo;
    }

    public void setPuestoDeTrabajo(PuestoDeTrabajoModel puestoDeTrabajo) {
        this.puestoDeTrabajo = puestoDeTrabajo;
    }

    public List<DireccionModel> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionModel> direcciones) {
        this.direcciones = direcciones;
    }

    public static UsuarioModel fromEntity(UsuarioEntity usuarioEntity) {
        return new UsuarioModel(
            usuarioEntity.getId(),
            usuarioEntity.getNick_usuario(),
            usuarioEntity.getContrasena(),
            usuarioEntity.getFecha_hora_creacion(),
            usuarioEntity.getGenero() != null ? GeneroModel.fromEntity(usuarioEntity.getGenero()) : null,
            usuarioEntity.getNombre(),
            usuarioEntity.getPrimer_apellido(),
            usuarioEntity.getSegundo_apellido(),
            usuarioEntity.getFecha_nacimiento(),
            usuarioEntity.getHora_desayuno(),
            usuarioEntity.getPuesto_trabajo() != null ? PuestoDeTrabajoModel.fromEntity(usuarioEntity.getPuesto_trabajo()) : null,
            usuarioEntity.getDirecciones() != null ? usuarioEntity.getDirecciones().stream().map(DireccionModel::fromEntity).toList() : null
        );
    }

    public static UsuarioEntity toNewEntity(UsuarioModel model) {
        return new UsuarioEntity(
            model.getNick_usuario(),
            model.getContrasena(),
            model.getFecha_hora_creacion(),
            model.getGenero() != null ? model.getGenero().toEntity() : null,
            model.getNombre(),
            model.getPrimer_apellido(),
            model.getSegundo_apellido(),
            model.getFecha_nacimiento(),
            model.getHora_desayuno(),
            model.getPuestoDeTrabajo() != null ? model.getPuestoDeTrabajo().toEntity() : null,
            model.getDirecciones() != null ? model.getDirecciones().stream().map(DireccionModel::toEntity).toList() : null
        );
    }

    public UsuarioGetDTO toGetDTO() {
        return new UsuarioGetDTO(
            this.id,
            this.nick_usuario,
            this.contrasena,
            this.fecha_hora_creacion,
            this.genero != null ? this.genero.toDTO() : null,
            this.nombre,
            this.primer_apellido,
            this.segundo_apellido,
            this.fecha_nacimiento,
            this.hora_desayuno,
            this.puestoDeTrabajo != null ? this.puestoDeTrabajo.toDTO() : null,
            this.direcciones != null ? this.direcciones.stream().map(DireccionModel::toDTO).toList() : null
        );
    }

     // public static UsuarioModel fromEntity(UsuarioEntity entity) {

    // public static fromDto(UsuarioDTO usuarioDTO) {
    //     return new UsuarioModel(
    //             usuarioDTO.getContrasena(),
    //             usuarioDTO.getFecha_hora_creacion(),
    //             usuarioDTO.getFecha_nacimiento(),
    //             usuarioDTO.getHora_desayuno(),
    //             null,
    //             usuarioDTO.getNick_usuario(),
    //             usuarioDTO.getNombre(),
    //             usuarioDTO.getPrimer_apellido(),
    //             usuarioDTO.getSegundo_apellido());
    // }

    // public static UsuarioModel fromPostDto(UsuarioPostDTO usuarioPostDTO) {
    //     return new UsuarioModel(
    //             usuarioPostDTO.getContrasena(),
    //             LocalDateTime.now(),
    //             usuarioPostDTO.getFecha_nacimiento(),
    //             usuarioPostDTO.getHora_desayuno(),
    //             null,
    //             usuarioPostDTO.getNick_usuario(),
    //             usuarioPostDTO.getNombre(),
    //             usuarioPostDTO.getPrimer_apellido(),
    //             usuarioPostDTO.getSegundo_apellido());
    // }


}
