package es.ediae.master.programacion.gestionusuario.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import es.ediae.master.programacion.gestionusuario.dtos.UsuarioGetDTO;
import es.ediae.master.programacion.gestionusuario.dtos.UsuarioPostDTO;
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
    private boolean esAdmin;
    private PuestoTrabajoModel puestoDeTrabajo;
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
            boolean esAdmin,
            PuestoTrabajoModel puestoDeTrabajo,
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
        this.esAdmin = esAdmin;
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

    public boolean isEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public PuestoTrabajoModel getPuestoTrabajo() {
        return puestoDeTrabajo;
    }

    public void setPuestoTrabajo(PuestoTrabajoModel puestoDeTrabajo) {
        this.puestoDeTrabajo = puestoDeTrabajo;
    }

    public List<DireccionModel> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionModel> direcciones) {
        this.direcciones = direcciones;
    }

    public static UsuarioModel fromPostDTO(UsuarioPostDTO dto) {

        GeneroModel genero = dto.getGeneroId() != null
        ? new GeneroModel(dto.getGeneroId(), null)
        : null;

        PuestoTrabajoModel puestoTrabajo = dto.getPuestoTrabajoId() != null
        ? new PuestoTrabajoModel(dto.getPuestoTrabajoId(), null)
        : null;

        return new UsuarioModel(
            null,
            dto.getNickUsuario(),
            dto.getContrasena(),
            LocalDateTime.now(),
            genero,
            dto.getNombre(),
            dto.getPrimerApellido(),
            dto.getSegundoApellido(),
            dto.getFechaNacimiento(),
            dto.getHoraDesayuno(),
            dto.isEsAdmin(),
            puestoTrabajo,
             null
        );
    }

    public static UsuarioEntity toEntity(UsuarioModel model) {
        return new UsuarioEntity(
            model.nickUsuario,
            model.contrasena,
            model.fechaHoraCreacion,
            model.genero != null ? model.genero.toEntity() : null,
            model.nombre,
            model.primerApellido,
            model.segundoApellido,
            model.fechaNacimiento,
            model.horaDesayuno,
            model.esAdmin,
            model.puestoDeTrabajo != null ? model.puestoDeTrabajo.toEntity() : null,
            model.direcciones != null ? model.direcciones.stream().map(DireccionModel::toEntity).toList() : null
        );
    }

    public static UsuarioModel fromEntity(UsuarioEntity usuarioEntity) {

        GeneroModel genero = usuarioEntity.getGenero() != null
        ? new GeneroModel(
            usuarioEntity.getGenero().getId(),
            usuarioEntity.getGenero().getNombre()
        )
        : null;

        PuestoTrabajoModel puestoTrabajo = usuarioEntity.getPuestoTrabajo() != null
        ? new PuestoTrabajoModel(
            usuarioEntity.getPuestoTrabajo().getId(),
            usuarioEntity.getPuestoTrabajo().getNombre()
        )
        : null;

        List<DireccionModel> direcciones = usuarioEntity.getDirecciones() != null
        ? usuarioEntity.getDirecciones().stream().map(DireccionModel::fromEntity).toList()
        : null;

        return new UsuarioModel(
            usuarioEntity.getId(),
            usuarioEntity.getNickUsuario(),
            usuarioEntity.getContrasena(),
            usuarioEntity.getFechaHoraCreacion(),
            genero,
            usuarioEntity.getNombre(),
            usuarioEntity.getPrimerApellido(),
            usuarioEntity.getSegundoApellido(),
            usuarioEntity.getFechaNacimiento(),
            usuarioEntity.getHoraDesayuno(),
            usuarioEntity.isEsAdmin(),
            puestoTrabajo,
            direcciones
        );
    }

    public UsuarioGetDTO toGetDTO() {
        return new UsuarioGetDTO(
            this.id,
            this.nickUsuario,
            this.contrasena,
            this.fechaHoraCreacion,
            this.genero != null ? this.genero.toGetDTO() : null,
            this.nombre,
            this.primerApellido,
            this.segundoApellido,
            this.fechaNacimiento,
            this.horaDesayuno,
            this.esAdmin,
            this.puestoDeTrabajo != null ? this.puestoDeTrabajo.toGetDTO() : null,
            this.direcciones != null ? this.direcciones.stream().map(DireccionModel::toGetDTO).toList() : null
        );
    }


}
