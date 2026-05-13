package es.ediae.master.programacion.gestionusuario.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import es.ediae.master.programacion.gestionusuario.service.DireccionModel;
import es.ediae.master.programacion.gestionusuario.service.GeneroModel;
import es.ediae.master.programacion.gestionusuario.service.PuestoTrabajoModel;
import es.ediae.master.programacion.gestionusuario.service.UsuarioModel;
import es.ediae.master.programacion.gestionusuario.service.impl.GeneroServiceImpl;
import es.ediae.master.programacion.gestionusuario.service.impl.PuestoTrabajoServiceImpl;
import es.ediae.master.programacion.gestionusuario.service.impl.UsuarioServiceImpl;
import jakarta.validation.Valid;



@RestController
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
    public List<UsuarioGetDTO> obtenerUsuarios(@RequestParam String nickUsuario, @RequestParam String contrasena) {

        usuarioService.verificarContrasena(new SesionDTO(nickUsuario, contrasena));

        List<UsuarioGetDTO> dtos = new ArrayList<>();
        for (UsuarioModel usuarioModel: usuarioService.obtenerTodosLosUsuarios()) {
            dtos.add(UsuarioGetDTO.fromModel(usuarioModel));
        }

        // Alternativa
        // return usuarioService.obtenerTodosLosUsuarios().stream()
        //         .map(UsuarioGetDTO::fromModel)
        //         .toList();

        return dtos;
    }

    @GetMapping("/{id}")
    public UsuarioGetDTO obtenerUsuario(@PathVariable Integer id, @RequestParam String nickUsuario, @RequestParam String contrasena) {

        usuarioService.verificarContrasena(new SesionDTO(nickUsuario, contrasena));

        return UsuarioGetDTO.fromModel(usuarioService.obtenerUsuarioPorId(id));
    }

    @GetMapping("/obtener-generos")
    public ResponseEntity<List<GeneroDTO>> obtenerGeneros(@RequestParam String nickUsuario, @RequestParam String contrasena) {

        usuarioService.verificarContrasena(new SesionDTO(nickUsuario, contrasena));

        List<GeneroModel> generos = this.generoService.obtenerGeneros();
        List<GeneroDTO> generosDTO = new ArrayList<>();
        for (GeneroModel generoModel: generos) {
            generosDTO.add(GeneroDTO.fromModel(generoModel));
        }

        return ResponseEntity.ok(generosDTO);
    }

    @GetMapping("/obtener-puestos")
    public ResponseEntity<List<PuestoTrabajoDTO>> obtenerPuestos(@RequestParam String nickUsuario, @RequestParam String contrasena) {

        usuarioService.verificarContrasena(new SesionDTO(nickUsuario, contrasena));

        List<PuestoTrabajoModel> puestos = this.puestoTrabajoService.obtenerPuestosDeTrabajo();
        List<PuestoTrabajoDTO> puestosDTO = new ArrayList<>();
        for (PuestoTrabajoModel puestoTrabajoModel: puestos) {
            puestosDTO.add(PuestoTrabajoDTO.fromModel(puestoTrabajoModel));
        }

        return ResponseEntity.ok(puestosDTO);
    }

    @GetMapping("/{id}/obtener-direcciones")
    public ResponseEntity<List<DireccionDTO>> obtenerDirecciones(@PathVariable Integer id, @RequestParam String nickUsuario, @RequestParam String contrasena) {

        usuarioService.verificarContrasena(new SesionDTO(nickUsuario, contrasena));

        List<DireccionModel> direccionModels = this.usuarioService.obtenerDireccionesPorUsuarioId(id);
        List<DireccionDTO> dtos = new ArrayList<>();
        for (DireccionModel direccionModel : direccionModels) {
            dtos.add(DireccionDTO.fromModel(direccionModel));
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
    public ResponseEntity<UsuarioGetDTO> actualizarUsuario(@Valid @PathVariable Integer id, @RequestBody UsuarioPostDTO requestDto, @RequestParam String nickUsuario, @RequestParam String contrasena) {

        usuarioService.verificarContrasena(new SesionDTO(nickUsuario, contrasena));

        UsuarioGetDTO usuarioGetDTO = this.usuarioService.actualizarUsuario(id, requestDto).toGetDTO();

        return ResponseEntity.ok(usuarioGetDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@Valid @PathVariable Integer id, @RequestParam String nickUsuario, @RequestParam String contrasena) {

        usuarioService.verificarContrasena(new SesionDTO(nickUsuario, contrasena));
        this.usuarioService.eliminarUsuario(id);

        return ResponseEntity.noContent().build();
    }
}
