package es.ediae.master.programacion.gestionusuario.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.repository.IPuestoTrabajoRepository;
import es.ediae.master.programacion.gestionusuario.service.IPuestoTrabajoService;
import es.ediae.master.programacion.gestionusuario.service.PuestoTrabajoModel;

@Service
public class PuestoTrabajoServiceImpl implements IPuestoTrabajoService {

    @Autowired
    private IPuestoTrabajoRepository puestoTrabajoRepository;

    @Override
    public PuestoTrabajoModel obtenerPuestoDeTrabajoPorId(Integer id) {
        return this.puestoTrabajoRepository.findById(id)
        .map(PuestoTrabajoModel::fromEntity)
        .orElseThrow(() -> new RuntimeException("Puesto de trabajo no encontrado con id: " + id));
    }

}
