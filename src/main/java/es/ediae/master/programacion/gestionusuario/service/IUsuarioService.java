package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import es.ediae.master.programacion.gestionusuario.dtos.DireccionDTO;
import es.ediae.master.programacion.gestionusuario.dtos.SesionDTO;
import es.ediae.master.programacion.gestionusuario.dtos.UsuarioPostDTO;
import es.ediae.master.programacion.gestionusuario.dtos.UsuarioPutDTO;

public interface IUsuarioService {

    @Query("SELECT u FROM UsuarioEntity u")
    public List<UsuarioModel> obtenerTodosLosUsuarios();

    @Query("SELECT u FROM UsuarioEntity u WHERE u.id = :id")
    public UsuarioModel obtenerUsuarioPorId(Integer id);

    public UsuarioModel obtenerUsuariosPorNickUsuario(String nickUsuario);

    public UsuarioModel crearUsuario(UsuarioPostDTO usuarioPostDTO);

    public UsuarioModel actualizarUsuario(UsuarioPutDTO usuarioPutDTO);

    public UsuarioModel ComproberContrasena(UsuarioModel usuarioModel, String contrasena);

    public UsuarioModel iniciarSesion(SesionDTO sesionDTO);

    public boolean eliminarUsuario(UsuarioPutDTO dto);

    public List<DireccionDTO> obtenerDireccionesPorUsuarioId(Integer id);
}
