package es.ediae.master.programacion.gestionusuario.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DireccionPostDTO {

    private Integer id;
    @NotBlank(message = "El nombre de la calle no puede estar vacío")
    private String nombreCalle;

    @NotBlank(message = "El número de calle no puede estar vacío")
    private String numeroCalle;

    private Boolean direccionPrincipal;
    private Integer usuarioId;

    public DireccionPostDTO() {
    }

    public DireccionPostDTO(
        Integer id,
        String nombreCalle,
        String numeroCalle,
        Boolean direccionPrincipal,
        Integer usuarioId
    ) {
        this.id = id;
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

    public Boolean isDireccionPrincipal() {
        return direccionPrincipal;
    }

    public void setDireccionPrincipal(Boolean direccionPrincipal) {
        this.direccionPrincipal = direccionPrincipal;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
