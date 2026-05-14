package es.ediae.master.programacion.gestionusuario.dtos;

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

    // Mapping is implemented in Models; DTO stays as POJO.
}
