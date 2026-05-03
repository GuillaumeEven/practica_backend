package es.ediae.master.programacion.gestionusuario.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import es.ediae.master.programacion.gestionusuario.dtos.UsuarioGetDTO;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;

public class UsuarioModel {

    private Integer id;
    private String nickUsuario;
    private String contrasena;
    private LocalDateTime fechaHoraCreacion;
    private GeneroModel genero;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private LocalDate fechaNacimiento;
    private LocalTime horaDesayuno;
    private PuestoDeTrabajoModel puestoDeTrabajo;
    private List<DireccionModel> direcciones;

    public UsuarioModel(
            Integer id,
            String nickUsuario,
            String contrasena,
            LocalDateTime fechaHoraCreacion,
            GeneroModel genero,
            String nombre,
            String primerApellido,
            String segundoApellido,
            LocalDate fechaNacimiento,
            LocalTime horaDesayuno,
            PuestoDeTrabajoModel puestoDeTrabajo,
            List<DireccionModel> direcciones
        ) {
        this.id = id;
        this.nickUsuario = nickUsuario;
        this.contrasena = contrasena;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.genero = genero;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.horaDesayuno = horaDesayuno;
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

    public String getNickUsuario() {
        return nickUsuario;
    }

    public void setNickUsuario(String nickUsuario) {
        this.nickUsuario = nickUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public LocalDateTime getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public GeneroModel getGeneroModel() {
        return genero;
    }

    public void setGenero(GeneroModel genero) {
        this.genero = genero;
    }

    public void setFechaHoraCreacion(LocalDateTime fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalTime getHoraDesayuno() {
        return horaDesayuno;
    }

    public void setHoraDesayuno(LocalTime horaDesayuno) {
        this.horaDesayuno = horaDesayuno;
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
            usuarioEntity.getNickUsuario(),
            usuarioEntity.getContrasena(),
            usuarioEntity.getFechaHoraCreacion(),
            usuarioEntity.getGenero() != null ? GeneroModel.fromEntity(usuarioEntity.getGenero()) : null,
            usuarioEntity.getNombre(),
            usuarioEntity.getPrimerApellido(),
            usuarioEntity.getSegundoApellido(),
            usuarioEntity.getFechaNacimiento(),
            usuarioEntity.getHoraDesayuno(),
            usuarioEntity.getPuestoTrabajo() != null ? PuestoDeTrabajoModel.fromEntity(usuarioEntity.getPuestoTrabajo()) : null,
            usuarioEntity.getDirecciones() != null ? usuarioEntity.getDirecciones().stream().map(DireccionModel::fromEntity).toList() : null
        );
    }

    public static UsuarioEntity toNewEntity(UsuarioModel model) {
        return new UsuarioEntity(
            model.getNickUsuario(),
            model.getContrasena(),
            model.getFechaHoraCreacion(),
            model.getGenero() != null ? model.getGenero().toEntity() : null,
            model.getNombre(),
            model.getPrimerApellido(),
            model.getSegundoApellido(),
            model.getFechaNacimiento(),
            model.getHoraDesayuno(),
            model.getPuestoDeTrabajo() != null ? model.getPuestoDeTrabajo().toEntity() : null,
            model.getDirecciones() != null ? model.getDirecciones().stream().map(DireccionModel::toEntity).toList() : null
        );
    }

    public UsuarioGetDTO toGetDTO() {
        return new UsuarioGetDTO(
            this.id,
            this.nickUsuario,
            this.contrasena,
            this.fechaHoraCreacion,
            this.genero != null ? this.genero.toDTO() : null,
            this.nombre,
            this.primerApellido,
            this.segundoApellido,
            this.fechaNacimiento,
            this.horaDesayuno,
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
