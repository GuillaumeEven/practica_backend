package es.ediae.master.programacion.gestionusuario.service;

public class PuestoDeTrabajoModel {

    private Integer id;
    private String nombre;

    public PuestoDeTrabajoModel() {
    }

    public PuestoDeTrabajoModel(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
