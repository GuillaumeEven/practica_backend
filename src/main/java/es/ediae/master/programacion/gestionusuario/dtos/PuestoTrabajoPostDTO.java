package es.ediae.master.programacion.gestionusuario.dtos;

public class PuestoTrabajoPostDTO {

    private String nombre;

    public PuestoTrabajoPostDTO() {
    }

    public PuestoTrabajoPostDTO(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
