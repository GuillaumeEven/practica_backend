package es.ediae.master.programacion.gestionusuario.dtos;

import es.ediae.master.programacion.gestionusuario.service.GeneroModel;

public class GeneroDTO {

    private Integer id;
    private String nombre;

    public GeneroDTO() {
    }

    public GeneroDTO(Integer id, String nombre) {
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

    public static GeneroDTO fromEntity(es.ediae.master.programacion.gestionusuario.entity.GeneroEntity generoEntity) {
        return new GeneroDTO(
                generoEntity.getId(),
                generoEntity.getNombre()
        );
    }

    public static GeneroDTO fromModel(es.ediae.master.programacion.gestionusuario.service.GeneroModel generoModel) {
        return new GeneroDTO(
                generoModel.getId(),
                generoModel.getNombre()
        );
    }

    public GeneroModel toModel() {
        return new GeneroModel(
                this.id,
                this.nombre
        );
    }
}
