package es.ediae.master.programacion.gestionusuario.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.read.ListAppender;
import es.ediae.master.programacion.gestionusuario.dtos.DireccionDTO;
import es.ediae.master.programacion.gestionusuario.dtos.SesionDTO;
import es.ediae.master.programacion.gestionusuario.dtos.UsuarioGetDTO;
import es.ediae.master.programacion.gestionusuario.dtos.UsuarioPostDTO;
import es.ediae.master.programacion.gestionusuario.dtos.UsuarioPutDTO;
import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;
import es.ediae.master.programacion.gestionusuario.repository.IUsuarioRepository;
import es.ediae.master.programacion.gestionusuario.service.DireccionModel;
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
        List<UsuarioEntity> entities = this.usuarioRepository.findAll();
        List<UsuarioModel> models = new ArrayList<>();
        for (UsuarioEntity entity : entities) {
            models.add(UsuarioModel.fromEntity(entity));
        }
        return models;

        // Alternativa
        // return this.usuarioRepository.findAll().stream()
        //         .map(UsuarioModel::fromEntity)
        //         .toList();
    }

    @Override
    public UsuarioModel obtenerUsuarioPorId(Integer id) {
        UsuarioEntity entity = this.usuarioRepository.findById(id).orElse(null);
        return entity != null ? UsuarioModel.fromEntity(entity) : null;
        // Alternativa
        // return this.usuarioRepository.findById(id)
        //         .map(UsuarioModel::fromEntity)
        //         .orElse(null);
    }

    public UsuarioModel obtenerUsuariosPorNickUsuario(String nickUsuario) {
        UsuarioEntity entity = usuarioRepository.findByNickUsuario(nickUsuario);
        return entity != null ? UsuarioModel.fromEntity(entity) : null;
    }

    @Override
    public UsuarioModel crearUsuario(UsuarioPostDTO usuarioPostDTO) {

        UsuarioModel usuarioModel = new UsuarioModel(
                null,
                usuarioPostDTO.getNickUsuario(),
                usuarioPostDTO.getContrasena(),
                LocalDateTime.now(),
                usuarioPostDTO.getGeneroId() != null ? generoService.obtenerGeneroPorId(usuarioPostDTO.getGeneroId()) : null,
                usuarioPostDTO.getNombre(),
                usuarioPostDTO.getPrimerApellido(),
                usuarioPostDTO.getSegundoApellido(),
                usuarioPostDTO.getFechaNacimiento(),
                usuarioPostDTO.getHoraDesayuno(),
                usuarioPostDTO.getPuestoTrabajoId() != null ? puestoDeTrabajoService.obtenerPuestoDeTrabajoPorId(usuarioPostDTO.getPuestoTrabajoId()) : null,
                usuarioPostDTO.getDireccionIds() != null ? usuarioPostDTO.getDireccionIds().stream().map(direccionId -> direccionService.obtenerDireccionPorId(direccionId)).toList() : null
        );
        UsuarioEntity savedEntity = this.usuarioRepository.save(UsuarioModel.toNewEntity(usuarioModel));
        return UsuarioModel.fromEntity(savedEntity);
    }

    @Override
    public UsuarioModel actualizarUsuario(UsuarioPutDTO usuarioPutDTO) {
        UsuarioEntity entity = usuarioRepository.findById(usuarioPutDTO.getId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            entity.setNickUsuario(usuarioPutDTO.getNickUsuario());
            entity.setContrasena(usuarioPutDTO.getContrasena());
            entity.setNombre(usuarioPutDTO.getNombre());
            entity.setPrimerApellido(usuarioPutDTO.getPrimerApellido());
            entity.setSegundoApellido(usuarioPutDTO.getSegundoApellido());
            entity.setFechaNacimiento(usuarioPutDTO.getFechaNacimiento());
            entity.setHoraDesayuno(usuarioPutDTO.getHoraDesayuno());
            entity.setGenero(usuarioPutDTO.getGeneroId() != null ? generoService.obtenerGeneroPorId(usuarioPutDTO.getGeneroId()).toEntity() : null);
            entity.setPuestoTrabajo(usuarioPutDTO.getPuestoTrabajoId() != null ? puestoDeTrabajoService.obtenerPuestoDeTrabajoPorId(usuarioPutDTO.getPuestoTrabajoId()).toEntity() : null);

            // Direcciones : récupérer les entités et s'assurer de setUsuario(...)
            List<DireccionEntity> direcciones = new ArrayList<>();
            if (usuarioPutDTO.getDireccionIds() != null) {
                for (Integer dirId : usuarioPutDTO.getDireccionIds()) {
                    DireccionModel dm = direccionService.obtenerDireccionPorId(dirId);
                    if (dm != null) {
                        DireccionEntity de = dm.toEntity();
                        de.setUsuario(entity);
                        direcciones.add(de);
                    }
                }
            }
            entity.setDirecciones(direcciones);

            UsuarioEntity saved = usuarioRepository.save(entity);
            return UsuarioModel.fromEntity(saved);
    }

    @Override
    public UsuarioModel ComproberContrasena(UsuarioModel usuarioModel, String contrasena) {
        return usuarioModel.getContrasena().equals(contrasena) ? usuarioModel : null;
    }

    @Override
    public boolean eliminarUsuario(UsuarioPutDTO dto) {
        UsuarioEntity entity = this.usuarioRepository.findById(dto.getId()).orElse(null);
        if (entity != null && entity.getContrasena().equals(dto.getContrasena())) {
            this.usuarioRepository.deleteById(dto.getId());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UsuarioModel iniciarSesion(SesionDTO sesionDTO) {
        // crear Usuario model
        UsuarioModel model = obtenerUsuariosPorNickUsuario(sesionDTO.getNickUsuario());
        if (model == null) {
            return null;
        }
        return model.getContrasena().equals(sesionDTO.getContrasena()) ? model : null;
    }

    @Override
    public List<DireccionDTO> obtenerDireccionesPorUsuarioId(Integer id) {
        List<DireccionDTO> direccionDTOS= new ArrayList<>();
        List<DireccionModel> direccionModels = obtenerUsuarioPorId(id).getDirecciones();
        for (DireccionModel direccionModel : direccionModels) {
            DireccionDTO dto = direccionModel.toDTO();
            direccionDTOS.add(dto);
        }
        return direccionDTOS;
    }
}
