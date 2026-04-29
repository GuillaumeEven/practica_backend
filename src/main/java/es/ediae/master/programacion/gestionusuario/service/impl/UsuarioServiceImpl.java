package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.repository.IUsuarioRepository;
import es.ediae.master.programacion.gestionusuario.service.IUsuarioService;
import es.ediae.master.programacion.gestionusuario.service.UsuarioModel;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioModel> obtenerTodosLosUsuarios() {
        return this.usuarioRepository.findAll().stream()
                .map(UsuarioModel::fromEntity)
                .toList();
    }

    @Override
    public UsuarioModel obtenerUsuarioPorId(Integer id) {
        return this.usuarioRepository.findById(id)
                .map(UsuarioModel::fromEntity)
                .orElse(null);
    }
}
