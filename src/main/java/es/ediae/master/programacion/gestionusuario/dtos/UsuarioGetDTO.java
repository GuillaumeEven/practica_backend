package es.ediae.master.programacion.gestionusuario.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import es.ediae.master.programacion.gestionusuario.service.UsuarioModel;

public class UsuarioGetDTO {

    private Integer id;
    private String nickUsuario;
    private String contrasena;
    private LocalDateTime fechaHoraCreacion;
    private GeneroDTO genero;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private LocalDate fechaNacimiento;
    private LocalTime horaDesayuno;
    private PuestoDeTrabajoDTO puestoDeTrabajo;
    private List<DireccionDTO> direcciones;


    public UsuarioGetDTO() {
    }

    public UsuarioGetDTO(
        Integer id,
        String nickUsuario,
        String contrasena,
        LocalDateTime fechaHoraCreacion,
        GeneroDTO genero,
        String nombre,
        String primerApellido,
        String segundoApellido,
        LocalDate fechaNacimiento,
        LocalTime horaDesayuno,
        PuestoDeTrabajoDTO puestoDeTrabajo,
        java.util.List<DireccionDTO> direcciones) {
        this.contrasena = contrasena;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.horaDesayuno = horaDesayuno;
        this.id = id;
        this.nickUsuario = nickUsuario;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.puestoDeTrabajo = puestoDeTrabajo;
        this.direcciones = direcciones;
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

    public void setFechaHoraCreacion(LocalDateTime fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    public GeneroDTO getGenero() {
        return genero;
    }

    public void setGenero(GeneroDTO genero) {
        this.genero = genero;
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

    public PuestoDeTrabajoDTO getPuestoDeTrabajo() {
        return puestoDeTrabajo;
    }

    public void setPuestoDeTrabajo(PuestoDeTrabajoDTO puestoDeTrabajo) {
        this.puestoDeTrabajo = puestoDeTrabajo;
    }

    public static UsuarioGetDTO fromModel(UsuarioModel model) {
        return new UsuarioGetDTO(
            model.getId(),
            model.getNickUsuario(),
            model.getContrasena(),
            model.getFechaHoraCreacion(),
            model.getGenero() != null ? GeneroDTO.fromModel(model.getGenero()) : null,
            model.getNombre(),
            model.getPrimerApellido(),
            model.getSegundoApellido(),
            model.getFechaNacimiento(),
            model.getHoraDesayuno(),
            model.getPuestoDeTrabajo() != null ? PuestoDeTrabajoDTO.fromEntity(model.getPuestoDeTrabajo().toEntity()) : null,
            // PuestoDeTrabajoDTO.fromEntity(model.getPuestoDeTrabajo() != null ? model.getPuestoDeTrabajo().toEntity() : null ),
            model.getDirecciones() != null ? model.getDirecciones().stream().map(DireccionDTO::fromModel).toList() : null
        );
    }





}
