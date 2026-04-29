package es.ediae.master.programacion.gestionusuario.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.repository.IGeneroRepository;
import es.ediae.master.programacion.gestionusuario.service.GeneroModel;
import es.ediae.master.programacion.gestionusuario.service.IGeneroService;

@Service
public class GeneroServiceImpl implements IGeneroService {

    @Autowired
    private IGeneroRepository generoRepository;

    @Override
    public GeneroModel obtenerGeneroPorId(Integer id) {
        return this.generoRepository.findById(id)
                .map(GeneroModel::fromEntity)
                .orElseThrow(() -> new RuntimeException("Genero no encontrado con id: " + id));
    }

}
