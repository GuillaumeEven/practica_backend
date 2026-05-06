package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.dtos.SesionDTO;
import es.ediae.master.programacion.gestionusuario.dtos.UsuarioPostDTO;
import es.ediae.master.programacion.gestionusuario.dtos.UsuarioPutDTO;
import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;
import es.ediae.master.programacion.gestionusuario.exception.GeneralException;
import es.ediae.master.programacion.gestionusuario.exception.UsuarioNoValidoException;
import es.ediae.master.programacion.gestionusuario.exception.WrongPasswordException;
import es.ediae.master.programacion.gestionusuario.repository.IUsuarioRepository;
import es.ediae.master.programacion.gestionusuario.service.DireccionModel;
import es.ediae.master.programacion.gestionusuario.service.GeneroModel;
import es.ediae.master.programacion.gestionusuario.service.IUsuarioService;
import es.ediae.master.programacion.gestionusuario.service.PuestoTrabajoModel;
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

    public UsuarioModel obtenerUsuarioPorNickUsuario(String nickUsuario) {
        UsuarioEntity entity = usuarioRepository.findByNickUsuario(nickUsuario);
        return entity != null ? UsuarioModel.fromEntity(entity) : null;
    }

    @Override
    public UsuarioModel crearUsuario(UsuarioPostDTO usuarioPostDTO) {
        if (usuarioRepository.findByNickUsuario(usuarioPostDTO.getNickUsuario()) != null &&
        usuarioPostDTO.getNickUsuario().equalsIgnoreCase(usuarioRepository.findByNickUsuario(usuarioPostDTO.getNickUsuario()).getNickUsuario())) {
            throw new UsuarioNoValidoException("El nick de usuario ya existe");
        }

        List<DireccionModel> direcciones = new ArrayList<>();
        if (usuarioPostDTO.getDireccionIds() != null && !usuarioPostDTO.getDireccionIds().isEmpty()) {
            for (Integer dirId : usuarioPostDTO.getDireccionIds()) {
                DireccionModel dirModel = direccionService.obtenerDireccionPorId(dirId).toModel();
                direcciones.add(dirModel);
            }
        } else {
            throw new UsuarioNoValidoException("El usuario debe tener al menos una dirección");
        }
        GeneroModel genero = generoService.obtenerGeneroPorId(usuarioPostDTO.getGeneroId());
        PuestoTrabajoModel puestoTrabajo = puestoDeTrabajoService.obtenerPuestoDeTrabajoPorId(usuarioPostDTO.getPuestoTrabajoId());

        UsuarioModel model = UsuarioModel.fromPostDTO(usuarioPostDTO);
        model.setGenero(genero);
        model.setPuestoTrabajo(puestoTrabajo);
        model.setDirecciones(direcciones);

        UsuarioEntity savedEntity = this.usuarioRepository.save(UsuarioModel.toNewEntity(model));
        return UsuarioModel.fromEntity(savedEntity);
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

        List<DireccionModel> direcciones  = new ArrayList<>();
        if (requestDto.getDireccionIds() != null && !requestDto.getDireccionIds().isEmpty()) {
            for (Integer dirId : requestDto.getDireccionIds()) {
                DireccionModel dirModel = direccionService.obtenerDireccionPorId(dirId).toModel();
                direcciones.add(dirModel);
            }
        } else {
            throw new UsuarioNoValidoException("El usuario debe tener al menos una dirección");
        }
        GeneroModel genero = generoService.obtenerGeneroPorId(requestDto.getGeneroId());
        PuestoTrabajoModel puestoTrabajo = puestoDeTrabajoService.obtenerPuestoDeTrabajoPorId(requestDto.getPuestoTrabajoId());

        UsuarioModel model = UsuarioModel.fromPostDTO(requestDto);

        model.setGenero(genero);
        model.setPuestoTrabajo(puestoTrabajo);
        model.setDirecciones(direcciones);

        entity = UsuarioModel.toNewEntity(model);
        entity.setId(id);

        UsuarioEntity saved = usuarioRepository.save(entity);
        return UsuarioModel.fromEntity(saved);
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
