package es.ediae.master.programacion.gestionusuario.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ediae.master.programacion.gestionusuario.dtos.DireccionDTO;
import es.ediae.master.programacion.gestionusuario.dtos.DireccionPostDTO;
import es.ediae.master.programacion.gestionusuario.model.DireccionModel;
import es.ediae.master.programacion.gestionusuario.repository.IDireccionRepository;
import es.ediae.master.programacion.gestionusuario.service.impl.DireccionServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("/api/v1/direcciones")
public class DireccionController {

    private static final Logger logger = Logger.getLogger(DireccionController.class.getName());


    @Autowired
    private DireccionServiceImpl direccionService;

    @Autowired
    private IDireccionRepository direccionRepository;

    @GetMapping()
    public ResponseEntity<List<DireccionDTO>> obtenerAllDirecciones() {
        List<DireccionModel> direcciones = direccionService.obtenerAllDirecciones();
        List<DireccionDTO> direccionDtos = new ArrayList<>();
        for (DireccionModel direccion : direcciones) {
            direccionDtos.add(direccion.toGetDTO());
        }
        return ResponseEntity.ok(direccionDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionDTO> obtenerDireccion(@PathVariable @Positive Integer id) {
        return ResponseEntity.ok(direccionService.obtenerDireccionPorId(id));
    }

    @PostMapping()
    public ResponseEntity<DireccionDTO> crearDireccion(@Valid @RequestBody DireccionPostDTO direccionPostDTO) {
        DireccionDTO direccionDTO =  direccionService.crearDireccion(direccionPostDTO);
        return ResponseEntity.ok(direccionDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DireccionDTO> actualizarDireccion(@Valid @RequestBody DireccionPostDTO requestDto, @PathVariable @Positive Integer id) {
        DireccionDTO direccionActualizada = direccionService.actualizarDireccion(id, requestDto);
        return ResponseEntity.ok(direccionActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDireccion(@PathVariable @Positive Integer id) {
        direccionService.eliminarDireccion(id);
        return ResponseEntity.noContent().build();
    }
}
