package es.ediae.master.programacion.gestionusuario.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ediae.master.programacion.gestionusuario.dtos.SesionDTO;
import es.ediae.master.programacion.gestionusuario.dtos.UsuarioGetDTO;
import es.ediae.master.programacion.gestionusuario.dtos.UsuarioPostDTO;
import es.ediae.master.programacion.gestionusuario.dtos.UsuarioPutDTO;
import es.ediae.master.programacion.gestionusuario.service.UsuarioModel;
import es.ediae.master.programacion.gestionusuario.service.impl.UsuarioServiceImpl;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping
    public List<UsuarioGetDTO> obtenerUsuarios() {
        List<UsuarioGetDTO> dtos = new ArrayList<>();
        for (UsuarioModel usuarioModel: usuarioService.obtenerTodosLosUsuarios()) {
            dtos.add(UsuarioGetDTO.fromModel(usuarioModel));
        }
        return dtos;
        // Alternativa
        // return usuarioService.obtenerTodosLosUsuarios().stream()
        //         .map(UsuarioGetDTO::fromModel)
        //         .toList();
    }

    @GetMapping("/{id}")
    public UsuarioGetDTO obtenerUsuario(@PathVariable Integer id) {
        return UsuarioGetDTO.fromModel(usuarioService.obtenerUsuarioPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioGetDTO> actualizarUsuario(@Valid @PathVariable Integer id, @RequestBody UsuarioPutDTO usuarioPutDTO) {
        // validar si existe una sesion
        UsuarioGetDTO usuarioGetDTO = this.usuarioService.actualizarUsuario(usuarioPutDTO).toGetDTO();
        return ResponseEntity.ok(usuarioGetDTO);
    }

    @PostMapping
    public ResponseEntity<UsuarioGetDTO> crearUsuario(@Valid @RequestBody UsuarioPostDTO usuarioPostDTO) {
        UsuarioGetDTO usuarioGetDTO = this.usuarioService.crearUsuario(usuarioPostDTO).toGetDTO();
        return ResponseEntity.created(null).body(usuarioGetDTO);
    }

    @PostMapping("/iniciar-sesion")
    public ResponseEntity<UsuarioGetDTO> iniciarSesion(@RequestBody SesionDTO sesionDTO) {

        UsuarioModel usuarioModel = this.usuarioService.obtenerUsuariosPorNickUsuario(sesionDTO.getNickUsuario());
        if (usuarioModel == null) {
            // Return 404 Not Found
            return ResponseEntity.notFound().build();
        }
        if (!usuarioModel.getContrasena().equals(sesionDTO.getContrasena())) {
            // Return 401 Unauthorized
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(usuarioModel.toGetDTO());
    }



}
