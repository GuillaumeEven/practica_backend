package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;

import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;

public interface IUsuarioService {

    public List<UsuarioEntity> obtenerTodosLosUsuarios();

}
