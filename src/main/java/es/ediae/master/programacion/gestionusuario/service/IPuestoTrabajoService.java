package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;

import es.ediae.master.programacion.gestionusuario.model.PuestoTrabajoModel;

public interface IPuestoTrabajoService {

    public PuestoTrabajoModel obtenerPuestoDeTrabajoPorId(Integer id);

    public List<PuestoTrabajoModel> obtenerPuestosDeTrabajo();

}
