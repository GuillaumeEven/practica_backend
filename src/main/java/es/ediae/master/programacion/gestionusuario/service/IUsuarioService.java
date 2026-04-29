package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public interface IUsuarioService {

    @Query("SELECT u FROM UsuarioEntity u")
    public List<UsuarioModel> obtenerTodosLosUsuarios();

    @Query("SELECT u FROM UsuarioEntity u WHERE u.id = :id")
    public UsuarioModel obtenerUsuarioPorId(Integer id);

}
