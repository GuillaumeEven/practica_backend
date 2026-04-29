```java
package es.ediae.master.programacion.gestionusuario.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;
import es.ediae.master.programacion.gestionusuario.entity.PuestoDeTrabajoEntity;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;

/**
 * Complete example model for Usuario.
 * This file is an example and can be copied into the service package.
 */
public class UsuarioModel {

    private Integer id;
    private String nick_usuario;
    private String contrasena;
    private LocalDateTime fecha_hora_creacion;
    private GeneroModel genero;
    private String nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private LocalDate fecha_nacimiento;
    private LocalTime hora_desayuno;
    private PuestoDeTrabajoModel puesto_trabajo;
    private List<DireccionModel> direcciones = new ArrayList<>();

    public UsuarioModel() {
    }

    public UsuarioModel(
            Integer id,
            String nick_usuario,
            String contrasena,
            LocalDateTime fecha_hora_creacion,
            GeneroModel genero,
            String nombre,
            String primer_apellido,
            String segundo_apellido,
            LocalDate fecha_nacimiento,
            LocalTime hora_desayuno,
            PuestoDeTrabajoModel puesto_trabajo,
            List<DireccionModel> direcciones) {
        this.id = id;
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
        this.direcciones = direcciones == null ? new ArrayList<>() : new ArrayList<>(direcciones);
    }

    // Getters / Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNick_usuario() { return nick_usuario; }
    public void setNick_usuario(String nick_usuario) { this.nick_usuario = nick_usuario; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public LocalDateTime getFecha_hora_creacion() { return fecha_hora_creacion; }
    public void setFecha_hora_creacion(LocalDateTime fecha_hora_creacion) { this.fecha_hora_creacion = fecha_hora_creacion; }

    public GeneroModel getGenero() { return genero; }
    public void setGenero(GeneroModel genero) { this.genero = genero; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPrimer_apellido() { return primer_apellido; }
    public void setPrimer_apellido(String primer_apellido) { this.primer_apellido = primer_apellido; }

    public String getSegundo_apellido() { return segundo_apellido; }
    public void setSegundo_apellido(String segundo_apellido) { this.segundo_apellido = segundo_apellido; }

    public LocalDate getFecha_nacimiento() { return fecha_nacimiento; }
    public void setFecha_nacimiento(LocalDate fecha_nacimiento) { this.fecha_nacimiento = fecha_nacimiento; }

    public LocalTime getHora_desayuno() { return hora_desayuno; }
    public void setHora_desayuno(LocalTime hora_desayuno) { this.hora_desayuno = hora_desayuno; }

    public PuestoDeTrabajoModel getPuesto_trabajo() { return puesto_trabajo; }
    public void setPuesto_trabajo(PuestoDeTrabajoModel puesto_trabajo) { this.puesto_trabajo = puesto_trabajo; }

    public List<DireccionModel> getDirecciones() { return direcciones; }
    public void setDirecciones(List<DireccionModel> direcciones) {
        this.direcciones = direcciones == null ? new ArrayList<>() : new ArrayList<>(direcciones);
    }

    // Mapping: Entity -> Model
    public static UsuarioModel fromEntity(UsuarioEntity e) {
        if (e == null) return null;

        UsuarioModel m = new UsuarioModel();
        m.setId(e.getId());
        m.setNick_usuario(e.getNick_usuario());
        m.setContrasena(e.getContrasena());
        m.setFecha_hora_creacion(e.getFecha_hora_creacion());
        m.setGenero(GeneroModel.fromEntity(e.getGenero()));
        m.setNombre(e.getNombre());
        m.setPrimer_apellido(e.getPrimer_apellido());
        m.setSegundo_apellido(e.getSegundo_apellido());
        m.setFecha_nacimiento(e.getFecha_nacimiento());
        m.setHora_desayuno(e.getHora_desayuno());
        m.setPuesto_trabajo(e.getPuesto_trabajo() == null ? null : PuestoDeTrabajoModel.fromEntity(e.getPuesto_trabajo()));

        if (e.getDirecciones() != null) {
            m.setDirecciones(
                e.getDirecciones().stream().map(d -> {
                    DireccionModel dm = new DireccionModel();
                    dm.setId(d.getId());
                    dm.setNombre_calle(d.getNombre_calle());
                    dm.setNumero_calle(d.getNumero_calle());
                    dm.setDireccion_principal(d.isDireccion_principal());
                    if (d.getUsuario() != null) dm.setUsuario_id(d.getUsuario().getId());
                    return dm;
                }).collect(Collectors.toList())
            );
        }

        return m;
    }

    // Mapping: Model -> Entity (useful for create/update)
    public UsuarioEntity toEntity() {
        PuestoDeTrabajoEntity puestoEntity = this.puesto_trabajo == null ? null : new PuestoDeTrabajoEntity(this.puesto_trabajo.getId(), this.puesto_trabajo.getNombre());
        UsuarioEntity entity = new UsuarioEntity(
                this.id,
                this.nick_usuario,
                this.contrasena,
                this.genero == null ? null : new es.ediae.master.programacion.gestionusuario.entity.GeneroEntity(this.genero.getId(), this.genero.getNombre()),
                this.nombre,
                this.primer_apellido,
                this.segundo_apellido,
                this.fecha_nacimiento,
                this.hora_desayuno,
                puestoEntity
        );
        // Note: fecha_hora_creacion is set in the entity constructor to LocalDateTime.now()
        // Direcciones mapping (if needed) should set usuario link on each DireccionEntity after creation.
        return entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioModel)) return false;
        UsuarioModel that = (UsuarioModel) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(nick_usuario, that.nick_usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nick_usuario);
    }

    @Override
    public String toString() {
        return "UsuarioModel{" +
                "id=" + id +
                ", nick_usuario='" + nick_usuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", primer_apellido='" + primer_apellido + '\'' +
                ", segundo_apellido='" + segundo_apellido + '\'' +
                '}';
    }
}
```