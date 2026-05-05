package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.entity.GeneroEntity;
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

    @Override
    public List<GeneroModel> obtenerGeneros() {
        List<GeneroEntity> generoEntities = this.generoRepository.findAll();
        List<GeneroModel> generos = new ArrayList<>();
        for (GeneroEntity generoEntity: generoEntities) {
            generos.add(GeneroModel.fromEntity(generoEntity));
        }
        return generos;
    }
}
