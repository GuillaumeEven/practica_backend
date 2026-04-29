package es.ediae.master.programacion.gestionusuario.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ediae.master.programacion.gestionusuario.dtos.UsuarioGetDTO;
import es.ediae.master.programacion.gestionusuario.dtos.UsuarioPostDTO;
import es.ediae.master.programacion.gestionusuario.service.impl.UsuarioServiceImpl;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping
    public List<UsuarioGetDTO> obtenerUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios().stream()
                .map(UsuarioGetDTO::fromModel)
                .toList();
    }

    @GetMapping("/{id}")
    public UsuarioGetDTO obtenerUsuario(@PathVariable Integer id) {
        return UsuarioGetDTO.fromModel(usuarioService.obtenerUsuarioPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioGetDTO> crearUsuario(@Valid @RequestBody UsuarioPostDTO usuarioPostDTO) {
        UsuarioGetDTO usuarioGetDTO = this.usuarioService.crearUsuario(usuarioPostDTO).toGetDTO();
        // respondemos con la dirección del nuevo recurso creado en el header Location y el recurso creado en el body
        URI location = URI.create(String.format("/api/v1/usuarios/%d", usuarioGetDTO.getId()));
        return ResponseEntity.created(location).body(usuarioGetDTO);
    }


}
