package es.ediae.master.programacion.gestionusuario.service;

import es.ediae.master.programacion.gestionusuario.dtos.DireccionDTO;
import es.ediae.master.programacion.gestionusuario.dtos.DireccionPostDTO;

public interface IDireccionService {

    public DireccionDTO obtenerDireccionPorId(Integer id);

    public DireccionDTO crearDireccion(DireccionPostDTO direccionPostDTO);

    public DireccionDTO actualizarDireccion(Integer id, DireccionPostDTO direccionPostDTO);

}
