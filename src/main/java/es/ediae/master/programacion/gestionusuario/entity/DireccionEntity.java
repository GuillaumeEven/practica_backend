package es.ediae.master.programacion.gestionusuario.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "direcciones")
public class DireccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre_calle;

    @Column(nullable = true)
    private String numero_calle;

    @Column(nullable = false)
    private boolean direccion_principal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    public DireccionEntity() {
    }

    public DireccionEntity(Integer id, String nombre_calle, String numero_calle, boolean direccion_principal) {
        this.id = id;
        this.nombre_calle = nombre_calle;
        this.numero_calle = numero_calle;
        this.direccion_principal = direccion_principal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre_calle() {
        return nombre_calle;
    }

    public void setNombre_calle(String nombre_calle) {
        this.nombre_calle = nombre_calle;
    }

    public String getNumero_calle() {
        return numero_calle;
    }

    public void setNumero_calle(String numero_calle) {
        this.numero_calle = numero_calle;
    }

    public boolean isDireccion_principal() {
        return direccion_principal;
    }

    public void setDireccion_principal(boolean direccion_principal) {
        this.direccion_principal = direccion_principal;
    }

}
