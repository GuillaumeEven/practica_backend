package es.ediae.master.programacion.gestionusuario.dtos;

public class SesionDTO {

    @NotEmpty(message = "El nickUsuario no puede estar vacío")
    private String nickUsuario;
    @NotEmpty(message = "La contraseña no puede estar vacía")
    private String contrasena;

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
