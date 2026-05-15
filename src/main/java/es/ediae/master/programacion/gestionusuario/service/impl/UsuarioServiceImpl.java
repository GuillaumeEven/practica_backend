package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.ediae.master.programacion.gestionusuario.constant.GeneralConstant;
import es.ediae.master.programacion.gestionusuario.dtos.DireccionPostDTO;
import es.ediae.master.programacion.gestionusuario.dtos.SesionDTO;
import es.ediae.master.programacion.gestionusuario.dtos.UsuarioPostDTO;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;
import es.ediae.master.programacion.gestionusuario.exception.GeneralException;
import es.ediae.master.programacion.gestionusuario.model.DireccionModel;
import es.ediae.master.programacion.gestionusuario.model.GeneroModel;
import es.ediae.master.programacion.gestionusuario.model.PuestoTrabajoModel;
import es.ediae.master.programacion.gestionusuario.model.UsuarioModel;
import es.ediae.master.programacion.gestionusuario.repository.IUsuarioRepository;
import es.ediae.master.programacion.gestionusuario.service.IUsuarioService;
import jakarta.persistence.EntityNotFoundException;

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
    }

    @Override
    public UsuarioModel obtenerUsuarioPorId(Integer id) {
        UsuarioEntity entity = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        return UsuarioModel.fromEntity(entity);
    }

    public UsuarioModel obtenerUsuarioPorNickUsuario(String nickUsuario) {
        UsuarioEntity entity = usuarioRepository.findByNickUsuario(nickUsuario);
        return entity != null ? UsuarioModel.fromEntity(entity) : null;
    }

    @Override
    @Transactional
    public UsuarioModel crearUsuario(UsuarioPostDTO usuarioPostDTO) {
        if (usuarioRepository.findByNickUsuario(usuarioPostDTO.getNickUsuario()) != null) {
            throw new GeneralException(
                GeneralConstant.USUARIO_NICK_YA_EXISTE_ERROR_CODE,
                GeneralConstant.USUARIO_NICK_YA_EXISTE_ERROR_MESSAGE
            );
        }

        if (usuarioPostDTO.getDirecciones() == null || usuarioPostDTO.getDirecciones().isEmpty()) {
            throw new GeneralException(
                GeneralConstant.USUARIO_NO_VALIDO_ERROR_CODE,
                "El usuario debe tener al menos una dirección"
            );
        }

        GeneroModel genero = generoService.obtenerGeneroPorId(usuarioPostDTO.getGeneroId());
        PuestoTrabajoModel puestoTrabajo = puestoDeTrabajoService.obtenerPuestoDeTrabajoPorId(usuarioPostDTO.getPuestoTrabajoId());

        UsuarioModel model = UsuarioModel.fromPostDTO(usuarioPostDTO);
        model.setGenero(genero);
        model.setPuestoTrabajo(puestoTrabajo);

        UsuarioEntity savedEntity = usuarioRepository.save(UsuarioModel.toEntity(model));

        for (DireccionPostDTO dirDTO : usuarioPostDTO.getDirecciones()) {
            dirDTO.setUsuarioId(savedEntity.getId());
            direccionService.crearDireccion(dirDTO);
        }

        UsuarioEntity reloaded = usuarioRepository.findById(savedEntity.getId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        return UsuarioModel.fromEntity(reloaded);
    }

    @Override
    public UsuarioModel actualizarUsuario(Integer id, UsuarioPostDTO requestDto) {
        usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        UsuarioEntity existingByNick = usuarioRepository.findByNickUsuario(requestDto.getNickUsuario());
        if (existingByNick != null && !id.equals(existingByNick.getId())) {
            throw new GeneralException(
                GeneralConstant.USUARIO_NICK_YA_EXISTE_ERROR_CODE,
                GeneralConstant.USUARIO_NICK_YA_EXISTE_ERROR_MESSAGE
            );
        }

        GeneroModel genero = generoService.obtenerGeneroPorId(requestDto.getGeneroId());
        PuestoTrabajoModel puestoTrabajo = puestoDeTrabajoService.obtenerPuestoDeTrabajoPorId(requestDto.getPuestoTrabajoId());

        UsuarioModel model = UsuarioModel.fromPostDTO(requestDto);
        model.setGenero(genero);
        model.setPuestoTrabajo(puestoTrabajo);

        UsuarioEntity entity = UsuarioModel.toEntity(model);
        entity.setId(id);

        UsuarioEntity saved = usuarioRepository.save(entity);

        if (requestDto.getDirecciones() != null && !requestDto.getDirecciones().isEmpty()) {
            for (DireccionPostDTO dirDTO : requestDto.getDirecciones()) {
                dirDTO.setUsuarioId(saved.getId());
                direccionService.crearDireccion(dirDTO);
            }
        }

        UsuarioEntity reloaded = usuarioRepository.findById(saved.getId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        return UsuarioModel.fromEntity(reloaded);
    }

    @Override
    public UsuarioModel ComproberContrasena(UsuarioModel usuarioModel, String contrasena) {
        return usuarioModel.getContrasena().equals(contrasena) ? usuarioModel : null;
    }

    @Override
    public void eliminarUsuario(Integer id) {
        usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioModel iniciarSesion(SesionDTO sesionDTO) {
        UsuarioModel model = obtenerUsuarioPorNickUsuario(sesionDTO.getNickUsuario());
        if (model == null) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
        return model.getContrasena().equals(sesionDTO.getContrasena()) ? model : null;
    }

    @Override
    public List<DireccionModel> obtenerDireccionesPorUsuarioId(Integer id) {
        List<DireccionModel> direccionModels = obtenerUsuarioPorId(id).getDirecciones();
        return direccionModels != null ? direccionModels : new ArrayList<>();
    }

    @Override
    public Boolean verificarContrasena(SesionDTO sesionDTO) {
        UsuarioModel model = obtenerUsuarioPorNickUsuario(sesionDTO.getNickUsuario());
        if (model == null) {
            throw new GeneralException(
                GeneralConstant.NO_ENCONTRADO_ERROR_CODE,
                GeneralConstant.USUARIO_NO_ENCONTRADO_ERROR_MESSAGE
            );
        }
        if (model.getContrasena().equals(sesionDTO.getContrasena())) {
            return true;
        } else {
            throw new GeneralException(
                GeneralConstant.CONTRASENA_NO_COINCIDE_ERROR_CODE,
                GeneralConstant.CONTRASENA_NO_COINCIDE_ERROR_MESSAGE
            );
        }
    }
}
