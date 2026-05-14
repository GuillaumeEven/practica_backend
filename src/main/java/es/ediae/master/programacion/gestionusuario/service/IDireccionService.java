package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;

import es.ediae.master.programacion.gestionusuario.dtos.DireccionDTO;
import es.ediae.master.programacion.gestionusuario.dtos.DireccionPostDTO;
import es.ediae.master.programacion.gestionusuario.model.DireccionModel;

public interface IDireccionService {

    public List<DireccionModel> obtenerAllDirecciones();

    public DireccionDTO obtenerDireccionPorId(Integer id);

    public DireccionDTO crearDireccion(DireccionPostDTO direccionPostDTO);

    public DireccionDTO actualizarDireccion(Integer id, DireccionPostDTO direccionPostDTO);

    public Boolean eliminarDireccion(Integer id);

}
