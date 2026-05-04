package es.ediae.master.programacion.gestionusuario.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.dtos.DireccionDTO;
import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;
import es.ediae.master.programacion.gestionusuario.repository.IDireccionRepository;
import es.ediae.master.programacion.gestionusuario.service.DireccionModel;
import es.ediae.master.programacion.gestionusuario.service.IDireccionService;

@Service
public class DireccionServiceImpl implements IDireccionService {

    @Autowired
    private IDireccionRepository direccionRepository;

    @Override
    public DireccionDTO obtenerDireccionPorId(Integer id) {
        DireccionEntity entity = this.direccionRepository.findById(id).orElseThrow();
        DireccionModel model = DireccionModel.fromEntity(entity);
        return DireccionDTO.fromModel(model);
    }

}
