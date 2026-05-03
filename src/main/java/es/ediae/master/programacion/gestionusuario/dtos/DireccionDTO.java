package es.ediae.master.programacion.gestionusuario.dtos;

import es.ediae.master.programacion.gestionusuario.service.DireccionModel;

public class DireccionDTO {

    private Integer id;
    private String nombreCalle;
    private String numeroCalle;
    private boolean direccionPrincipal;
    private Integer usuarioId;

    public DireccionDTO() {
    }

    public DireccionDTO(
        Integer id,
        String nombreCalle,
        String numeroCalle,
        boolean direccionPrincipal,
        Integer usuarioId
    ) {
        this.id = id;
        this.nombreCalle = nombreCalle;
        this.numeroCalle = numeroCalle;
        this.direccionPrincipal = direccionPrincipal;
        this.usuarioId = usuarioId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCalle() {
        return nombreCalle;
    }

    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }

    public String getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(String numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    public boolean isDireccionPrincipal() {
        return direccionPrincipal;
    }

    public void setDireccionPrincipal(boolean direccionPrincipal) {
        this.direccionPrincipal = direccionPrincipal;
    }

    public static DireccionDTO fromModel(DireccionModel direccionModel) {
        return new DireccionDTO(
                direccionModel.getId(),
                direccionModel.getNombreCalle(),
                direccionModel.getNumeroCalle(),
                direccionModel.isDireccionPrincipal(),
                direccionModel.getUsuarioId()
        );
    }

    public DireccionModel toModel() {
        return new DireccionModel(
                this.id,
                this.nombreCalle,
                this.numeroCalle,
                this.direccionPrincipal,
                this.usuarioId
        );
    }

}
