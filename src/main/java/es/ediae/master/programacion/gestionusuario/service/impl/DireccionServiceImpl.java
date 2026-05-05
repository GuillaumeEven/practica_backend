package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.dtos.DireccionDTO;
import es.ediae.master.programacion.gestionusuario.dtos.DireccionPostDTO;
import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;
import es.ediae.master.programacion.gestionusuario.repository.IDireccionRepository;
import es.ediae.master.programacion.gestionusuario.repository.IUsuarioRepository;
import es.ediae.master.programacion.gestionusuario.service.DireccionModel;
import es.ediae.master.programacion.gestionusuario.service.IDireccionService;

@Service
public class DireccionServiceImpl implements IDireccionService {

    private static final Logger logger = Logger.getLogger(DireccionServiceImpl.class.getName());
    @Autowired
    private IDireccionRepository direccionRepository;

    @Autowired
    public IUsuarioRepository usuarioRepository;

    @Override
    public DireccionDTO obtenerDireccionPorId(Integer id) {
        DireccionEntity entity = this.direccionRepository.findById(id).orElseThrow();
        DireccionModel model = DireccionModel.fromEntity(entity);
        return DireccionDTO.fromModel(model);
    }

    @Override
    public DireccionDTO crearDireccion(DireccionPostDTO direccionPostDTO) {
        DireccionModel model = direccionPostDTO.toModel();
        Optional<UsuarioEntity> usuarioEntity = this.usuarioRepository.findById(direccionPostDTO.getUsuarioId());
        DireccionEntity entity = DireccionModel.toEntity(model);
        if (usuarioEntity.isPresent()) {
                entity.setUsuario(usuarioEntity.get());
        } else {
                logger.warning("Usuario con ID " + direccionPostDTO.getUsuarioId() + " no encontrado. No se asignará usuario a la dirección.");
        }
        DireccionEntity savedEntity = this.direccionRepository.save(entity);
        DireccionModel savedModel = DireccionModel.fromEntity(savedEntity);
        return DireccionDTO.fromModel(savedModel);
    }

    @Override
    public DireccionDTO actualizarDireccion(Integer id, DireccionPostDTO direccionPostDTO) {
        DireccionEntity entity = this.direccionRepository.findById(id).orElseThrow();
        entity.setNombreCalle(direccionPostDTO.getNombreCalle());
        entity.setNumeroCalle(direccionPostDTO.getNumeroCalle());
        entity.setDireccionPrincipal(direccionPostDTO.isDireccionPrincipal());
        Optional<UsuarioEntity> usuarioEntity = this.usuarioRepository.findById(direccionPostDTO.getUsuarioId());
        if (usuarioEntity.isPresent()) {
                entity.setUsuario(usuarioEntity.get());
        } else {
                logger.warning("Usuario con ID " + direccionPostDTO.getUsuarioId() + " no encontrado. No se actualizará el usuario de la dirección.");
        }
        DireccionEntity updatedEntity = this.direccionRepository.save(entity);
        DireccionModel updatedModel = DireccionModel.fromEntity(updatedEntity);
        return DireccionDTO.fromModel(updatedModel);
    }

    @Override
    public Boolean eliminarDireccion(Integer id) {
        if (!this.direccionRepository.existsById(id)) {
            logger.warning("Dirección con ID " + id + " no encontrada. No se puede eliminar.");
            return false;
        }
        this.direccionRepository.deleteById(id);
        return true;
    }
}
