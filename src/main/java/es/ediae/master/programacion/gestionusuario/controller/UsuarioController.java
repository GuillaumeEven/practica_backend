package es.ediae.master.programacion.gestionusuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ediae.master.programacion.gestionusuario.dtos.UsuarioGetDTO;
import es.ediae.master.programacion.gestionusuario.service.impl.UsuarioServiceImpl;

import org.springframework.web.bind.annotation.RequestParam;


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

}
