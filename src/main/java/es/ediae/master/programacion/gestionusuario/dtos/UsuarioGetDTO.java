package es.ediae.master.programacion.gestionusuario.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import es.ediae.master.programacion.gestionusuario.service.UsuarioModel;

public class UsuarioGetDTO {

    private Integer id;
    private String nick_usuario;
    private String contrasena;
    private LocalDateTime fecha_hora_creacion;
    private GeneroDTO genero;
    private String nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private LocalDate fecha_nacimiento;
    private LocalTime hora_desayuno;
    private PuestoDeTrabajoDTO puestoDeTrabajo;
    private List<DireccionDTO> direcciones;


    public UsuarioGetDTO() {
    }

    public UsuarioGetDTO(
        Integer id,
        String nick_usuario,
        String contrasena,
        LocalDateTime fecha_hora_creacion,
        GeneroDTO genero,
        String nombre,
        String primer_apellido,
        String segundo_apellido,
        LocalDate fecha_nacimiento,
        LocalTime hora_desayuno,
        PuestoDeTrabajoDTO puestoDeTrabajo,
        java.util.List<DireccionDTO> direcciones) {
        this.contrasena = contrasena;
        this.fecha_hora_creacion = fecha_hora_creacion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.genero = genero;
        this.hora_desayuno = hora_desayuno;
        this.id = id;
        this.nick_usuario = nick_usuario;
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.puestoDeTrabajo = puestoDeTrabajo;
        this.direcciones = direcciones;
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

    public void setFecha_hora_creacion(LocalDateTime fecha_hora_creacion) {
        this.fecha_hora_creacion = fecha_hora_creacion;
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

    public PuestoDeTrabajoDTO getPuestoDeTrabajo() {
        return puestoDeTrabajo;
    }

    public void setPuestoDeTrabajo(PuestoDeTrabajoDTO puestoDeTrabajo) {
        this.puestoDeTrabajo = puestoDeTrabajo;
    }

    public static UsuarioGetDTO fromModel(UsuarioModel model) {
        return new UsuarioGetDTO(
            model.getId(),
            model.getNick_usuario(),
            model.getContrasena(),
            model.getFecha_hora_creacion(),
            model.getGenero() != null ? GeneroDTO.fromModel(model.getGenero()) : null,
            model.getNombre(),
            model.getPrimer_apellido(),
            model.getSegundo_apellido(),
            model.getFecha_nacimiento(),
            model.getHora_desayuno(),
            PuestoDeTrabajoDTO.fromEntity(model.getPuestoDeTrabajo() != null ? model.getPuestoDeTrabajo().toEntity() : null ),
            model.getDirecciones() != null ? model.getDirecciones().stream().map(DireccionDTO::fromModel).toList() : null
        );
    }





}
