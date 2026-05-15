package es.ediae.master.programacion.gestionusuario.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotEmpty;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SesionDTO {

    @NotEmpty(message = "El nickUsuario no puede estar vacío")
    private String nickUsuario;
    @NotEmpty(message = "La contraseña no puede estar vacía")
    private String contrasena;

    public SesionDTO() {
    }

    public SesionDTO(String nickUsuario, String contrasena) {
        this.nickUsuario = nickUsuario;
        this.contrasena = contrasena;
    }

    public String getNickUsuario() {
        return nickUsuario;
    }

    public void setNickUsuario(String nickUsuario) {
        this.nickUsuario = nickUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
