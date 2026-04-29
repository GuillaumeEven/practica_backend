package es.ediae.master.programacion.gestionusuario.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.repository.IDireccionRepository;
import es.ediae.master.programacion.gestionusuario.service.DireccionModel;
import es.ediae.master.programacion.gestionusuario.service.IDireccionService;

@Service
public class DireccionServiceImpl implements IDireccionService {

    @Autowired
    private IDireccionRepository direccionRepository;

    @Override
    public DireccionModel obtenerDireccionPorId(Integer id) {
        return this.direccionRepository.findById(id)
                .map(DireccionModel::fromEntity)
                .orElseThrow(() -> new RuntimeException("Direccion no encontrada con id: " + id));
    }

}
