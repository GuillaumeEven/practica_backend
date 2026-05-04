package es.ediae.master.programacion.gestionusuario.dtos;

import es.ediae.master.programacion.gestionusuario.service.PuestoTrabajoModel;

public class PuestoTrabajoDTO {

    private Integer id;
    private String nombre;

    public PuestoTrabajoDTO() {
    }

    public PuestoTrabajoDTO(Integer id, String nombre) {
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

    public static PuestoTrabajoDTO fromEntity(es.ediae.master.programacion.gestionusuario.entity.PuestoDeTrabajoEntity entity) {
        return new PuestoTrabajoDTO(
                entity.getId(),
                entity.getNombre()
        );
    }

    public PuestoTrabajoModel toModel() {
        return new PuestoTrabajoModel(
                this.id,
                this.nombre
        );
    }

}
