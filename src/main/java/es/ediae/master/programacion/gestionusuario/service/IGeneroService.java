package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;

public interface IGeneroService {

    public GeneroModel obtenerGeneroPorId(Integer id);

    public List<GeneroModel> obtenerGeneros();

}
