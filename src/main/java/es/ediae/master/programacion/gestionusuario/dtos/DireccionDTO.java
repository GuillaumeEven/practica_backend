package es.ediae.master.programacion.gestionusuario.dtos;

public class DireccionDTO {

    private Integer id;
    private String nombreCalle;
    private String numeroCalle;
    private Boolean direccionPrincipal;
    private Integer usuarioId;

    public DireccionDTO() {
    }

    public DireccionDTO(
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

    // DTOs stay as POJOs; mapping is handled by Models.

}
