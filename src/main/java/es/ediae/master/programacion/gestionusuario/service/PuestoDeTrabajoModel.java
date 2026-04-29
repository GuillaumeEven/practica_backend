package es.ediae.master.programacion.gestionusuario.service;

import es.ediae.master.programacion.gestionusuario.dtos.PuestoDeTrabajoDTO;
import es.ediae.master.programacion.gestionusuario.entity.PuestoDeTrabajoEntity;

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

    public static PuestoDeTrabajoModel fromEntity(PuestoDeTrabajoEntity entity) {
        return new PuestoDeTrabajoModel(entity.getId(), entity.getNombre());
    }

    public PuestoDeTrabajoEntity toEntity() {
        PuestoDeTrabajoEntity entity = new PuestoDeTrabajoEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        return entity;
    }

    public PuestoDeTrabajoDTO toDTO() {
        return new PuestoDeTrabajoDTO(this.id, this.nombre);
    }

}
