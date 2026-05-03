package es.ediae.master.programacion.gestionusuario.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotEmpty;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UsuarioPostDTO {

    @NotEmpty(message = "El nickUsuario no puede estar vacío")
    private String nickUsuario;
    private String contrasena;
    private LocalDateTime fechaHoraCreacion;
    private Integer generoId;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private LocalDate fechaNacimiento;
    private LocalTime horaDesayuno;
    private Integer puestoTrabajoId;
    private List<Integer> direccionIds;


    public UsuarioPostDTO() {
    }

    public UsuarioPostDTO(
        String nickUsuario,
        String contrasena,
        LocalDateTime fechaHoraCreacion,
        Integer generoId,
        String nombre,
        String primerApellido,
        String segundoApellido,
        LocalDate fechaNacimiento,
        LocalTime horaDesayuno,
        Integer puestoTrabajoId,
        java.util.List<Integer> direccionIds) {
        this.contrasena = contrasena;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.fechaNacimiento = fechaNacimiento;
        this.generoId = generoId;
        this.horaDesayuno = horaDesayuno;
        this.nickUsuario = nickUsuario;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.puestoTrabajoId = puestoTrabajoId;
        this.direccionIds = direccionIds;
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

    public Integer getPuestoTrabajoId() {
        return puestoTrabajoId;
    }

    public void setPuestoTrabajoId(Integer puestoTrabajoId) {
        this.puestoTrabajoId = puestoTrabajoId;
    }

    public List<Integer> getDireccionIds() {
        return direccionIds;
    }

    public void setDireccionIds(List<Integer> direccionIds) {
        this.direccionIds = direccionIds;
    }

    // public static UsuarioPostDTO fromModel(UsuarioModel model) {
    //     return new UsuarioPostDTO(
    //         model.getNick_usuario(),
    //         model.getContrasena(),
    //         model.getFecha_hora_creacion(),
    //         model.getGenero() != null ? GeneroDTO.fromModel(model.getGenero()) : null,
    //         model.getNombre(),
    //         model.getPrimer_apellido(),
    //         model.getSegundo_apellido(),
    //         model.getFecha_nacimiento(),
    //         model.getHora_desayuno(),
    //         PuestoDeTrabajoDTO.fromEntity(model.getPuestoDeTrabajo() != null ? model.getPuestoDeTrabajo().toEntity() : null ),
    //         model.getDirecciones() != null ? model.getDirecciones().stream().map(DireccionDTO::fromModel).toList() : null
    //     );
}
