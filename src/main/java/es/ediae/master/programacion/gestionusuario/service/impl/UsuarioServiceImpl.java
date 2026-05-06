package es.ediae.master.programacion.gestionusuario.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.dtos.DireccionDTO;
import es.ediae.master.programacion.gestionusuario.dtos.SesionDTO;
import es.ediae.master.programacion.gestionusuario.dtos.UsuarioPostDTO;
import es.ediae.master.programacion.gestionusuario.dtos.UsuarioPutDTO;
import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;
import es.ediae.master.programacion.gestionusuario.exception.UsuarioNoValidoException;
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
        if (usuarioRepository.findByNickUsuario(usuarioPostDTO.getNickUsuario()) != null &&
        usuarioPostDTO.getNickUsuario().equalsIgnoreCase(usuarioRepository.findByNickUsuario(usuarioPostDTO.getNickUsuario()).getNickUsuario())) {
            throw new UsuarioNoValidoException("El nick de usuario ya existe");
        }
        Logger.getLogger(UsuarioServiceImpl.class.getName()).info("Creando usuario con direcciones: " + usuarioPostDTO.getDireccionIds());

        List<DireccionModel> direcciones = new ArrayList<>();
        if (usuarioPostDTO.getDireccionIds() != null && !usuarioPostDTO.getDireccionIds().isEmpty()) {
            for (Integer dirId : usuarioPostDTO.getDireccionIds()) {
                DireccionModel dirModel = direccionService.obtenerDireccionPorId(dirId).toModel();
                direcciones.add(dirModel);
            }
        } else {
            throw new UsuarioNoValidoException("El usuario debe tener al menos una dirección");
        }

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
            usuarioPostDTO.isEsAdmin(),
            usuarioPostDTO.getPuestoTrabajoId() != null ? puestoDeTrabajoService.obtenerPuestoDeTrabajoPorId(usuarioPostDTO.getPuestoTrabajoId()) : null,
            direcciones
        );
        UsuarioEntity savedEntity = this.usuarioRepository.save(UsuarioModel.toNewEntity(usuarioModel));
        return UsuarioModel.fromEntity(savedEntity);
    }

    @Override
    public UsuarioModel actualizarUsuario(UsuarioPutDTO usuarioPutDTO) {
        UsuarioEntity entity = usuarioRepository.findById(usuarioPutDTO.getId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuarioPutDTO.getNickUsuario().equalsIgnoreCase(entity.getNickUsuario())) {
            throw new UsuarioNoValidoException("El nick de usuario ya existe");
        }

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
                    DireccionModel dirModel = direccionService.obtenerDireccionPorId(dirId).toModel();
                    DireccionEntity dirEntity = DireccionModel.toEntity(dirModel);
                    if (dirEntity != null) {
                        dirEntity.setUsuario(entity); // Associer la dirección à l'utilisateur
                        direcciones.add(dirEntity);
                    }
                }
                entity.setDirecciones(direcciones);
            } else {
                throw new UsuarioNoValidoException("El usuario debe tener al menos una dirección");
            }


            UsuarioEntity saved = usuarioRepository.save(entity);
            return UsuarioModel.fromEntity(saved);
    }

    @Override
    public UsuarioModel ComproberContrasena(UsuarioModel usuarioModel, String contrasena) {
        return usuarioModel.getContrasena().equals(contrasena) ? usuarioModel : null;
    }

    @Override
    public boolean eliminarUsuario(SesionDTO dto, Integer id) {
        UsuarioEntity entity = this.usuarioRepository.findById(id).orElse(null);
        if (entity != null && entity.getContrasena().equals(dto.getContrasena())) {
            this.usuarioRepository.deleteById(id);
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
