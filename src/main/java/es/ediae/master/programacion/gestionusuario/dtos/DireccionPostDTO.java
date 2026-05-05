package es.ediae.master.programacion.gestionusuario.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import es.ediae.master.programacion.gestionusuario.service.DireccionModel;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DireccionPostDTO {

    private String nombreCalle;
    private String numeroCalle;
    private boolean direccionPrincipal;
    private Integer usuarioId;

    public DireccionPostDTO() {
    }

    public DireccionPostDTO(
        String nombreCalle,
        String numeroCalle,
        boolean direccionPrincipal,
        Integer usuarioId
    ) {
        this.nombreCalle = nombreCalle;
        this.numeroCalle = numeroCalle;
        this.direccionPrincipal = direccionPrincipal;
        this.usuarioId = usuarioId;
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

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public DireccionModel toModel() {
        return new DireccionModel(
            this.nombreCalle,
            this.numeroCalle,
            this.direccionPrincipal,
            this.usuarioId
        );
    }

}
