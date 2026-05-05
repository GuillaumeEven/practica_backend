package es.ediae.master.programacion.gestionusuario.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ediae.master.programacion.gestionusuario.dtos.DireccionDTO;
import es.ediae.master.programacion.gestionusuario.dtos.DireccionPostDTO;
import es.ediae.master.programacion.gestionusuario.repository.IDireccionRepository;
import es.ediae.master.programacion.gestionusuario.service.impl.DireccionServiceImpl;

@RestController
@RequestMapping("/api/v1/direcciones")
public class DireccionController {

    private static final Logger logger = Logger.getLogger(DireccionController.class.getName());


    @Autowired
    private DireccionServiceImpl direccionService;

    @Autowired
    private IDireccionRepository direccionRepository;

    @GetMapping("/{id}")
    public ResponseEntity<DireccionDTO> obtenerDireccion(@PathVariable Integer id) {
        return ResponseEntity.ok(direccionService.obtenerDireccionPorId(id));
    }

    @PostMapping()
    public ResponseEntity<DireccionDTO> crearDireccion(@RequestBody DireccionPostDTO direccionPostDTO) {
        DireccionDTO direccionDTO =  direccionService.crearDireccion(direccionPostDTO);
        return ResponseEntity.ok(direccionDTO);
    }
}
