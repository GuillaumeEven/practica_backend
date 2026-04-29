package es.ediae.master.programacion.gestionusuario.dtos;

public class PuestoDeTrabajoDTO {

    private Integer id;
    private String nombre;

    public PuestoDeTrabajoDTO() {
    }

    public PuestoDeTrabajoDTO(Integer id, String nombre) {
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

    public static PuestoDeTrabajoDTO fromEntity(es.ediae.master.programacion.gestionusuario.entity.PuestoDeTrabajoEntity entity) {
        return new PuestoDeTrabajoDTO(
                entity.getId(),
                entity.getNombre()
        );
    }

}
