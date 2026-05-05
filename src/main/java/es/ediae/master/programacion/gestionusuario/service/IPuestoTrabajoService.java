package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;

public interface IPuestoTrabajoService {

    public PuestoTrabajoModel obtenerPuestoDeTrabajoPorId(Integer id);

    public List<PuestoTrabajoModel> obtenerPuestosDeTrabajo();

}
