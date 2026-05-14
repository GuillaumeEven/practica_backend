package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;

import es.ediae.master.programacion.gestionusuario.model.GeneroModel;

public interface IGeneroService {

    public GeneroModel obtenerGeneroPorId(Integer id);

    public List<GeneroModel> obtenerGeneros();

}
