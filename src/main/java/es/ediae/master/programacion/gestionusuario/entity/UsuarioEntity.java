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

    @Column(name = "nick_usuario", nullable = false)
    private String nickUsuario;

    @Column(nullable = false)
    private String contrasena;

    @Column(name = "fecha_hora_creacion", nullable = false)
    private LocalDateTime fechaHoraCreacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "genero_id", nullable = false)
    private GeneroEntity genero;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "primer_apellido", nullable = false)
    private String primerApellido;

    @Column(name = "segundo_apellido", nullable = true)
    private String segundoApellido;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "hora_desayuno", nullable = true)
    private LocalTime horaDesayuno;

    @Column(name = "es_admin", nullable = false)
    private boolean esAdmin;

    @ManyToOne(optional = true)
    @JoinColumn(name = "puesto_trabajo_id", nullable = true)
    private PuestoDeTrabajoEntity puestoTrabajo;

    @OneToMany(mappedBy = "usuario")
    private java.util.List<DireccionEntity> direcciones;


    public UsuarioEntity() {
    }

    public UsuarioEntity(
        Integer id,
        String nickUsuario,
        String contrasena,
        GeneroEntity genero,
        String nombre,
        String primerApellido,
        String segundoApellido,
        LocalDate fechaNacimiento,
        LocalTime horaDesayuno,
        PuestoDeTrabajoEntity puestoTrabajo) {
        this.id = id;
        this.nickUsuario = nickUsuario;
        this.contrasena = contrasena;
        this.genero = genero;
        this.fechaHoraCreacion = LocalDateTime.now();
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.horaDesayuno = horaDesayuno;
        this.puestoTrabajo = puestoTrabajo;
    }

    public UsuarioEntity(
        String nickUsuario,
        String contrasena,
        LocalDateTime fechaHoraCreacion,
        GeneroEntity genero,
        String nombre,
        String primerApellido,
        String segundoApellido,
        LocalDate fechaNacimiento,
        LocalTime horaDesayuno,
        boolean esAdmin,
        PuestoDeTrabajoEntity puestoTrabajo,
        java.util.List<DireccionEntity> direcciones) {
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
            this.puestoTrabajo = puestoTrabajo;
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

    public PuestoDeTrabajoEntity getPuestoTrabajo() {
        return puestoTrabajo;
    }

    public void setPuestoTrabajo(PuestoDeTrabajoEntity puestoTrabajo) {
        this.puestoTrabajo = puestoTrabajo;
    }

    public java.util.List<DireccionEntity> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(java.util.List<DireccionEntity> direcciones) {
        this.direcciones = direcciones;
    }
}
