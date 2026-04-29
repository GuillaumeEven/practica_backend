package es.ediae.master.programacion.gestionusuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;
import es.ediae.master.programacion.gestionusuario.repository.IDireccionRepository;

@RestController
@RequestMapping("/api/v1/direcciones")
public class DireccionController {

    @Autowired
    private IDireccionRepository direccionRepository;

    @GetMapping("/{usuarioId}")
    public List<DireccionEntity> getDireccionesByUsuarioId(@PathVariable Integer usuarioId) {
        return direccionRepository.findByUsuarioId(usuarioId);
    }
}
