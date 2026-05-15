package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.ediae.master.programacion.gestionusuario.dtos.DireccionPostDTO;
import es.ediae.master.programacion.gestionusuario.dtos.SesionDTO;
import es.ediae.master.programacion.gestionusuario.dtos.UsuarioPostDTO;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;
import es.ediae.master.programacion.gestionusuario.exception.GeneralException;
import es.ediae.master.programacion.gestionusuario.exception.UsuarioNoValidoException;
import es.ediae.master.programacion.gestionusuario.exception.WrongPasswordException;
import es.ediae.master.programacion.gestionusuario.model.DireccionModel;
import es.ediae.master.programacion.gestionusuario.model.GeneroModel;
import es.ediae.master.programacion.gestionusuario.model.PuestoTrabajoModel;
import es.ediae.master.programacion.gestionusuario.model.UsuarioModel;
import es.ediae.master.programacion.gestionusuario.repository.IUsuarioRepository;
import es.ediae.master.programacion.gestionusuario.service.IUsuarioService;

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

    public UsuarioModel obtenerUsuarioPorNickUsuario(String nickUsuario) {
        UsuarioEntity entity = usuarioRepository.findByNickUsuario(nickUsuario);
        return entity != null ? UsuarioModel.fromEntity(entity) : null;
    }

    @Override
    @Transactional
    public UsuarioModel crearUsuario(UsuarioPostDTO usuarioPostDTO) {
        if (usuarioRepository.findByNickUsuario(usuarioPostDTO.getNickUsuario()) != null) {
            throw new UsuarioNoValidoException("El nick de usuario ya existe");
        }

        if (usuarioPostDTO.getDirecciones() == null || usuarioPostDTO.getDirecciones().isEmpty()) {
            throw new UsuarioNoValidoException("El usuario debe tener al menos una dirección");
        }

        GeneroModel genero = generoService.obtenerGeneroPorId(usuarioPostDTO.getGeneroId());
        PuestoTrabajoModel puestoTrabajo = puestoDeTrabajoService.obtenerPuestoDeTrabajoPorId(usuarioPostDTO.getPuestoTrabajoId());

        UsuarioModel model = UsuarioModel.fromPostDTO(usuarioPostDTO);
        model.setGenero(genero);
        model.setPuestoTrabajo(puestoTrabajo);

        // 1st save: persist the user to get its generated ID
        UsuarioEntity savedEntity = usuarioRepository.save(UsuarioModel.toEntity(model));

        // 2nd save: persist each direction now that we have the user ID
        for (DireccionPostDTO dirDTO : usuarioPostDTO.getDirecciones()) {
            dirDTO.setUsuarioId(savedEntity.getId());
            direccionService.crearDireccion(dirDTO);
        }

        // Reload to return the full user with its directions
        return UsuarioModel.fromEntity(usuarioRepository.findById(savedEntity.getId()).orElseThrow());
    }

    @Override
    public UsuarioModel actualizarUsuario(Integer id, UsuarioPostDTO requestDto) {
        UsuarioEntity entity = usuarioRepository.findById(id).orElseThrow(() -> new GeneralException(404, "Usuario no encontrado"));
        UsuarioEntity existingByNick = usuarioRepository.findByNickUsuario(requestDto.getNickUsuario());

        if (
            existingByNick != null &&
            !id.equals(existingByNick.getId())
        ) {
            throw new UsuarioNoValidoException("El nick de usuario ya existe");
        }

        GeneroModel genero = generoService.obtenerGeneroPorId(requestDto.getGeneroId());
        PuestoTrabajoModel puestoTrabajo = puestoDeTrabajoService.obtenerPuestoDeTrabajoPorId(requestDto.getPuestoTrabajoId());

        UsuarioModel model = UsuarioModel.fromPostDTO(requestDto);
        model.setGenero(genero);
        model.setPuestoTrabajo(puestoTrabajo);

        entity = UsuarioModel.toEntity(model);
        entity.setId(id);

        UsuarioEntity saved = usuarioRepository.save(entity);

        // If the request includes new directions, create them linked to this user
        if (requestDto.getDirecciones() != null) {
        for (DireccionPostDTO dirDTO : requestDto.getDirecciones()) {
            if (dirDTO.getId() != null) {
            direccionService.actualizarDireccion(dirDTO.getId(), dirDTO);
            } else {
            dirDTO.setUsuarioId(saved.getId());
            direccionService.crearDireccion(dirDTO);
            }
        }
        }

        return UsuarioModel.fromEntity(usuarioRepository.findById(saved.getId()).orElseThrow());
    }

    @Override
    public UsuarioModel ComproberContrasena(UsuarioModel usuarioModel, String contrasena) {
        return usuarioModel.getContrasena().equals(contrasena) ? usuarioModel : null;
    }

    @Override
    public void eliminarUsuario(Integer id) {
        UsuarioEntity entity = this.usuarioRepository.findById(id).orElse(null);
        if (entity != null) {
            this.usuarioRepository.deleteById(id);
        } else {
            throw new GeneralException(404, "Usuario no encontrado");
        }
    }

    @Override
    public UsuarioModel iniciarSesion(SesionDTO sesionDTO) {
        // crear Usuario model
        UsuarioModel model = obtenerUsuarioPorNickUsuario(sesionDTO.getNickUsuario());
        if (model == null) {
            return null;
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
            throw new GeneralException(404, "Usuario no encontrado");
        }
        if (model.getContrasena().equals(sesionDTO.getContrasena())) {
            return true;
        } else {
            throw new WrongPasswordException();
        }
    }
}
