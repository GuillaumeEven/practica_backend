package es.ediae.master.programacion.gestionusuario.model;

import es.ediae.master.programacion.gestionusuario.dtos.GeneroDTO;
import es.ediae.master.programacion.gestionusuario.entity.GeneroEntity;

public class GeneroModel {

    private Integer id;
    private String nombre;

    public GeneroModel() {
    }

    public GeneroModel(Integer id, String nombre) {
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

    public static GeneroModel fromEntity(GeneroEntity generoEntity) {
        if (generoEntity == null) return null;
        return new GeneroModel(generoEntity.getId(), generoEntity.getNombre());
    }

    public GeneroEntity toEntity() {
        return new GeneroEntity(this.id, this.nombre);
    }

    public GeneroDTO toGetDTO() {
        return new GeneroDTO(this.id, this.nombre);
    }

}
