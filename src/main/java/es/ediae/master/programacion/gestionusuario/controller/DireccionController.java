package es.ediae.master.programacion.gestionusuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ediae.master.programacion.gestionusuario.dtos.DireccionDTO;
import es.ediae.master.programacion.gestionusuario.repository.IDireccionRepository;
import es.ediae.master.programacion.gestionusuario.service.impl.DireccionServiceImpl;

@RestController
@RequestMapping("/api/v1/direcciones")
public class DireccionController {

    @Autowired
    private DireccionServiceImpl direccionService;

    @Autowired
    private IDireccionRepository direccionRepository;

    @GetMapping("/{id}")
    public DireccionDTO obtenerDireccion(@PathVariable Integer id) {
        return direccionService.obtenerDireccionPorId(id);
    }
}
