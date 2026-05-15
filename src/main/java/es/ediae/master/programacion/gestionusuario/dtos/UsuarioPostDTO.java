package es.ediae.master.programacion.gestionusuario.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UsuarioPostDTO {

    @NotBlank(message = "El nickUsuario no puede estar vacío")
    private String nickUsuario;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String contrasena;

    @NotNull(message = "El generoId es obligatorio")
    private Integer generoId;

    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private LocalDate fechaNacimiento;
    private LocalTime horaDesayuno;
    private Boolean esAdmin;

    @NotNull(message = "El puestoTrabajoId es obligatorio")
    private Integer puestoTrabajoId;

    @Valid
    @NotEmpty(message = "El usuario debe tener al menos una dirección")
    private List<DireccionPostDTO> direcciones;


    public UsuarioPostDTO() {
    }

    public UsuarioPostDTO(
        String nickUsuario,
        String contrasena,
        Integer generoId,
        String nombre,
        String primerApellido,
        String segundoApellido,
        LocalDate fechaNacimiento,
        LocalTime horaDesayuno,
        Boolean esAdmin,
        Integer puestoTrabajoId,
        List<DireccionPostDTO> direcciones) {
        this.nickUsuario = nickUsuario;
        this.contrasena = contrasena;
        this.generoId = generoId;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.horaDesayuno = horaDesayuno;
        this.esAdmin = esAdmin;
        this.puestoTrabajoId = puestoTrabajoId;
        this.direcciones = direcciones;
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

    public Integer getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Integer generoId) {
        this.generoId = generoId;
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

    public Integer getPuestoTrabajoId() {
        return puestoTrabajoId;
    }

    public void setPuestoTrabajoId(Integer puestoTrabajoId) {
        this.puestoTrabajoId = puestoTrabajoId;
    }

    public List<DireccionPostDTO> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionPostDTO> direcciones) {
        this.direcciones = direcciones;
    }

}
