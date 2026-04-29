package es.ediae.master.programacion.gestionusuario.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.dtos.UsuarioPostDTO;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;
import es.ediae.master.programacion.gestionusuario.repository.IUsuarioRepository;
import es.ediae.master.programacion.gestionusuario.service.IUsuarioService;
import es.ediae.master.programacion.gestionusuario.service.UsuarioModel;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private GeneroServiceImpl generoService;

    @Autowired
    private PuestoTrabajoServiceImpl puestoDeTrabajoService;

    @Autowired
    private DireccionServiceImpl direccionService;

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

    @Override
    public UsuarioModel crearUsuario(UsuarioPostDTO usuarioPostDTO) {

        UsuarioModel usuarioModel = new UsuarioModel(
                null,
                usuarioPostDTO.getNick_usuario(),
                usuarioPostDTO.getContrasena(),
                LocalDateTime.now(),
                usuarioPostDTO.getGenero_id() != null ? generoService.obtenerGeneroPorId(usuarioPostDTO.getGenero_id()) : null,
                usuarioPostDTO.getNombre(),
                usuarioPostDTO.getPrimer_apellido(),
                usuarioPostDTO.getSegundo_apellido(),
                usuarioPostDTO.getFecha_nacimiento(),
                usuarioPostDTO.getHora_desayuno(),
                usuarioPostDTO.getPuesto_trabajo_id() != null ? puestoDeTrabajoService.obtenerPuestoDeTrabajoPorId(usuarioPostDTO.getPuesto_trabajo_id()) : null,
                usuarioPostDTO.getDireccion_ids() != null ? usuarioPostDTO.getDireccion_ids().stream().map(direccionId -> direccionService.obtenerDireccionPorId(direccionId)).toList() : null
        );
        UsuarioEntity savedEntity = this.usuarioRepository.save(UsuarioModel.toNewEntity(usuarioModel));
        return UsuarioModel.fromEntity(savedEntity);
    }
}
