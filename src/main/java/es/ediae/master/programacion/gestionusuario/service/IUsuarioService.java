package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;

import es.ediae.master.programacion.gestionusuario.dtos.DireccionDTO;
import es.ediae.master.programacion.gestionusuario.dtos.SesionDTO;
import es.ediae.master.programacion.gestionusuario.dtos.UsuarioPostDTO;
import es.ediae.master.programacion.gestionusuario.dtos.UsuarioPutDTO;

public interface IUsuarioService {

    public List<UsuarioModel> obtenerTodosLosUsuarios();

    public UsuarioModel obtenerUsuarioPorId(Integer id);

    public UsuarioModel obtenerUsuariosPorNickUsuario(String nickUsuario);

    public UsuarioModel crearUsuario(UsuarioPostDTO usuarioPostDTO);

    public UsuarioModel actualizarUsuario(UsuarioPutDTO usuarioPutDTO);

    public UsuarioModel ComproberContrasena(UsuarioModel usuarioModel, String contrasena);

    public UsuarioModel iniciarSesion(SesionDTO sesionDTO);

    public boolean eliminarUsuario(SesionDTO dto, Integer id);

    public List<DireccionDTO> obtenerDireccionesPorUsuarioId(Integer id);
}
