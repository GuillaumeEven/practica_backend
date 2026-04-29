package es.ediae.master.programacion.gestionusuario.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public class UsuarioPostDTO {

    @NotEmpty(message = "El nick_usuario no puede estar vacío")
    private String nick_usuario;
    private String contrasena;
    private LocalDateTime fecha_hora_creacion;
    private Integer genero_id;
    private String nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private LocalDate fecha_nacimiento;
    private LocalTime hora_desayuno;
    private Integer puesto_trabajo_id;
    private List<Integer> direccion_ids;


    public UsuarioPostDTO() {
    }

    public UsuarioPostDTO(
        String nick_usuario,
        String contrasena,
        LocalDateTime fecha_hora_creacion,
        Integer genero_id,
        String nombre,
        String primer_apellido,
        String segundo_apellido,
        LocalDate fecha_nacimiento,
        LocalTime hora_desayuno,
        Integer puesto_trabajo_id,
        java.util.List<Integer> direccion_ids) {
        this.contrasena = contrasena;
        this.fecha_hora_creacion = fecha_hora_creacion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.genero_id = genero_id;
        this.hora_desayuno = hora_desayuno;
        this.nick_usuario = nick_usuario;
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.puesto_trabajo_id = puesto_trabajo_id;
        this.direccion_ids = direccion_ids;
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

    public Integer getGenero_id() {
        return genero_id;
    }

    public void setGenero_id(Integer genero_id) {
        this.genero_id = genero_id;
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

    public Integer getPuesto_trabajo_id() {
        return puesto_trabajo_id;
    }

    public void setPuesto_trabajo_id(Integer puesto_trabajo_id) {
        this.puesto_trabajo_id = puesto_trabajo_id;
    }

    public List<Integer> getDireccion_ids() {
        return direccion_ids;
    }

    public void setDireccion_ids(List<Integer> direccion_ids) {
        this.direccion_ids = direccion_ids;
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
