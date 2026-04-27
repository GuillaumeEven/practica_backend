package es.ediae.master.programacion.gestionusuario.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nick_usuario;
    @Column(nullable = false)
    private String contrasena;
    @Column(nullable = false)
    private LocalDateTime fecha_hora_creacion;
    // Genero
    // @Column(nullable = false)
    // private Genero genero:
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String primer_apellido;
    @Column(nullable = true)
    private String segundo_apellido;
    @Column(nullable = false)
    private LocalDate fecha_nacimiento;
    @Column(nullable = true)
    private LocalTime hora_desayuno;
    // Puesto de trabajo
    // @Column(nullable = true)
    // private Puesto_trabajo puesto_trabajo;


    public UsuarioEntity(
        Long id,
        String nick_usuario,
        String contrasena,
        LocalDateTime fecha_hora_creacion,
        String nombre,
        String primer_apellido,
        String segundo_apellido,
        LocalDate fecha_nacimiento,
        LocalTime hora_desayuno) {
        this.id = id;
        this.nick_usuario = nick_usuario;
        this.contrasena = contrasena;
        this.fecha_hora_creacion = fecha_hora_creacion;
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.hora_desayuno = hora_desayuno;
    }

    public UsuarioEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
