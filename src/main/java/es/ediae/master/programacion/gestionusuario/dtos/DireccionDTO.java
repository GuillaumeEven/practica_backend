package es.ediae.master.programacion.gestionusuario.dtos;

import es.ediae.master.programacion.gestionusuario.service.DireccionModel;

public class DireccionDTO {

    private Integer id;
    private String nombre_calle;
    private String numero_calle;
    private boolean direccion_principal;

    public DireccionDTO() {
    }

    public DireccionDTO(Integer id, String nombre_calle, String numero_calle, boolean direccion_principal) {
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

    public static DireccionDTO fromModel(DireccionModel direccionModel) {
        return new DireccionDTO(
                direccionModel.getId(),
                direccionModel.getNombre_calle(),
                direccionModel.getNumero_calle(),
                direccionModel.isDireccion_principal()
        );
    }

}
