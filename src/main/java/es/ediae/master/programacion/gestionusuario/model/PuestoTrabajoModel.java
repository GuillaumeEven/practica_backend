package es.ediae.master.programacion.gestionusuario.model;

import es.ediae.master.programacion.gestionusuario.dtos.PuestoTrabajoDTO;
import es.ediae.master.programacion.gestionusuario.entity.PuestoDeTrabajoEntity;

public class PuestoTrabajoModel {

    private Integer id;
    private String nombre;

    public PuestoTrabajoModel() {
    }

    public PuestoTrabajoModel(Integer id, String nombre) {
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

    public static PuestoTrabajoModel fromEntity(PuestoDeTrabajoEntity entity) {
        return new PuestoTrabajoModel(entity.getId(), entity.getNombre());
    }

    public PuestoDeTrabajoEntity toEntity() {
        PuestoDeTrabajoEntity entity = new PuestoDeTrabajoEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        return entity;
    }

    public PuestoTrabajoDTO toGetDTO() {
        return new PuestoTrabajoDTO(this.id, this.nombre);
    }

}
