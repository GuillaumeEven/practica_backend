package es.ediae.master.programacion.gestionusuario.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.ediae.master.programacion.gestionusuario.dtos.DireccionDTO;
import es.ediae.master.programacion.gestionusuario.dtos.GeneroDTO;
import es.ediae.master.programacion.gestionusuario.dtos.PuestoTrabajoDTO;
import es.ediae.master.programacion.gestionusuario.dtos.SesionDTO;
import es.ediae.master.programacion.gestionusuario.dtos.UsuarioGetDTO;
import es.ediae.master.programacion.gestionusuario.dtos.UsuarioPostDTO;
import es.ediae.master.programacion.gestionusuario.model.DireccionModel;
import es.ediae.master.programacion.gestionusuario.model.GeneroModel;
import es.ediae.master.programacion.gestionusuario.model.PuestoTrabajoModel;
import es.ediae.master.programacion.gestionusuario.model.UsuarioModel;
import es.ediae.master.programacion.gestionusuario.service.impl.GeneroServiceImpl;
import es.ediae.master.programacion.gestionusuario.service.impl.PuestoTrabajoServiceImpl;
import es.ediae.master.programacion.gestionusuario.service.impl.UsuarioServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


@RestController
@Validated
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private GeneroServiceImpl generoService;

    @Autowired
    private PuestoTrabajoServiceImpl puestoTrabajoService;


    @GetMapping
    public List<UsuarioGetDTO> obtenerUsuarios(@RequestParam @NotBlank String nickUsuario, @RequestParam @NotBlank String contrasena) {

        usuarioService.verificarContrasena(new SesionDTO(nickUsuario, contrasena));

        List<UsuarioGetDTO> dtos = new ArrayList<>();
        for (UsuarioModel usuarioModel: usuarioService.obtenerTodosLosUsuarios()) {
            dtos.add(usuarioModel.toGetDTO());
        }

        return dtos;
    }

    @GetMapping("/{id}")
    public UsuarioGetDTO obtenerUsuario(@PathVariable @Positive Integer id, @RequestParam @NotBlank String nickUsuario, @RequestParam @NotBlank String contrasena) {

        usuarioService.verificarContrasena(new SesionDTO(nickUsuario, contrasena));

        return usuarioService.obtenerUsuarioPorId(id) != null ? usuarioService.obtenerUsuarioPorId(id).toGetDTO() : null;
    }

    @GetMapping("/obtener-generos")
    public ResponseEntity<List<GeneroDTO>> obtenerGeneros(@RequestParam @NotBlank String nickUsuario, @RequestParam @NotBlank String contrasena) {

        usuarioService.verificarContrasena(new SesionDTO(nickUsuario, contrasena));

        List<GeneroModel> generos = this.generoService.obtenerGeneros();
        List<GeneroDTO> generosDTO = new ArrayList<>();
        for (GeneroModel generoModel: generos) {
            generosDTO.add(generoModel.toGetDTO());
        }

        return ResponseEntity.ok(generosDTO);
    }

    @GetMapping("/obtener-puestos")
    public ResponseEntity<List<PuestoTrabajoDTO>> obtenerPuestos(@RequestParam @NotBlank String nickUsuario, @RequestParam @NotBlank String contrasena) {

        usuarioService.verificarContrasena(new SesionDTO(nickUsuario, contrasena));

        List<PuestoTrabajoModel> puestos = this.puestoTrabajoService.obtenerPuestosDeTrabajo();
        List<PuestoTrabajoDTO> puestosDTO = new ArrayList<>();
        for (PuestoTrabajoModel puestoTrabajoModel: puestos) {
            puestosDTO.add(puestoTrabajoModel.toGetDTO());
        }

        return ResponseEntity.ok(puestosDTO);
    }

    @GetMapping("/{id}/obtener-direcciones")
    public ResponseEntity<List<DireccionDTO>> obtenerDirecciones(@PathVariable @Positive Integer id, @RequestParam @NotBlank String nickUsuario, @RequestParam @NotBlank String contrasena) {

        usuarioService.verificarContrasena(new SesionDTO(nickUsuario, contrasena));

        List<DireccionModel> direccionModels = this.usuarioService.obtenerDireccionesPorUsuarioId(id);
        List<DireccionDTO> dtos = new ArrayList<>();
        for (DireccionModel direccionModel : direccionModels) {
            dtos.add(direccionModel.toGetDTO());
        }

        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<UsuarioGetDTO> crearUsuario(@Valid @RequestBody UsuarioPostDTO usuarioPostDTO) {

        UsuarioGetDTO usuarioGetDTO = this.usuarioService.crearUsuario(usuarioPostDTO).toGetDTO();

        return ResponseEntity.created(null).body(usuarioGetDTO);
    }

    @PostMapping("/iniciar-sesion")
    public ResponseEntity<UsuarioGetDTO> iniciarSesion(@Valid @RequestBody SesionDTO sesionDTO) {

        usuarioService.verificarContrasena(sesionDTO);

        UsuarioModel usuarioModel = this.usuarioService.obtenerUsuarioPorNickUsuario(sesionDTO.getNickUsuario());

        return ResponseEntity.ok(usuarioModel.toGetDTO());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioGetDTO> actualizarUsuario(@PathVariable @Positive Integer id, @Valid @RequestBody UsuarioPostDTO requestDto, @RequestParam @NotBlank String nickUsuario, @RequestParam @NotBlank String contrasena) {

        usuarioService.verificarContrasena(new SesionDTO(nickUsuario, contrasena));

        UsuarioGetDTO usuarioGetDTO = this.usuarioService.actualizarUsuario(id, requestDto).toGetDTO();

        return ResponseEntity.ok(usuarioGetDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable @Positive Integer id, @RequestParam @NotBlank String nickUsuario, @RequestParam @NotBlank String contrasena) {

        usuarioService.verificarContrasena(new SesionDTO(nickUsuario, contrasena));
        this.usuarioService.eliminarUsuario(id);

        return ResponseEntity.noContent().build();
    }
}
