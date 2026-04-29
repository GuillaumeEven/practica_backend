package es.ediae.master.programacion.gestionusuario.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nick_usuario;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private LocalDateTime fecha_hora_creacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "genero_id", nullable = false)
    private GeneroEntity genero;

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

    @ManyToOne(optional = true)
    @JoinColumn(name = "puesto_trabajo_id", nullable = true)
    private PuestoDeTrabajoEntity puesto_trabajo;

    @OneToMany(mappedBy = "usuario")
    private java.util.List<DireccionEntity> direcciones;


    public UsuarioEntity() {
    }

    public UsuarioEntity(
        Integer id,
        String nick_usuario,
        String contrasena,
        GeneroEntity genero,
        String nombre,
        String primer_apellido,
        String segundo_apellido,
        LocalDate fecha_nacimiento,
        LocalTime hora_desayuno,
        PuestoDeTrabajoEntity puesto_trabajo) {
        this.id = id;
        this.nick_usuario = nick_usuario;
        this.contrasena = contrasena;
        this.genero = genero;
        this.fecha_hora_creacion = LocalDateTime.now();
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.hora_desayuno = hora_desayuno;
        this.puesto_trabajo = puesto_trabajo;
    }

    public UsuarioEntity(
        String nick_usuario,
        String contrasena,
        LocalDateTime fecha_hora_creacion,
        GeneroEntity genero,
        String nombre,
        String primer_apellido,
        String segundo_apellido,
        LocalDate fecha_nacimiento,
        LocalTime hora_desayuno,
        PuestoDeTrabajoEntity puesto_trabajo,
        java.util.List<DireccionEntity> direcciones) {
         this.nick_usuario = nick_usuario;
         this.contrasena = contrasena;
         this.fecha_hora_creacion = fecha_hora_creacion;
         this.genero = genero;
         this.nombre = nombre;
         this.primer_apellido = primer_apellido;
         this.segundo_apellido = segundo_apellido;
         this.fecha_nacimiento = fecha_nacimiento;
         this.hora_desayuno = hora_desayuno;
         this.puesto_trabajo = puesto_trabajo;
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

    public GeneroEntity getGenero() {
        return genero;
    }

    public void setGenero(GeneroEntity genero) {
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

    public PuestoDeTrabajoEntity getPuesto_trabajo() {
        return puesto_trabajo;
    }

    public void setPuesto_trabajo(PuestoDeTrabajoEntity puesto_trabajo) {
        this.puesto_trabajo = puesto_trabajo;
    }

    public java.util.List<DireccionEntity> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(java.util.List<DireccionEntity> direcciones) {
        this.direcciones = direcciones;
    }
}
