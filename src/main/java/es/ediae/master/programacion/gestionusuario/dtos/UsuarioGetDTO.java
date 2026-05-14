package es.ediae.master.programacion.gestionusuario.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
    private Boolean esAdmin;
    private PuestoTrabajoDTO puestoDeTrabajo;
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
        Boolean esAdmin,
        PuestoTrabajoDTO puestoDeTrabajo,
        java.util.List<DireccionDTO> direcciones) {
        this.contrasena = contrasena;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.horaDesayuno = horaDesayuno;
        this.esAdmin = esAdmin;
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

    public Boolean isEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(Boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public PuestoTrabajoDTO getPuestoTrabajo() {
        return puestoDeTrabajo;
    }

    public void setPuestoTrabajo(PuestoTrabajoDTO puestoDeTrabajo) {
        this.puestoDeTrabajo = puestoDeTrabajo;
    }

    public List<DireccionDTO> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionDTO> direcciones) {
        this.direcciones = direcciones;
    }

    // Mapping handled in Models; keep DTO as response POJO.
}
