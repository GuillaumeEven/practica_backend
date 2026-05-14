package es.ediae.master.programacion.gestionusuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;
import es.ediae.master.programacion.gestionusuario.entity.GeneroEntity;
import es.ediae.master.programacion.gestionusuario.entity.PuestoDeTrabajoEntity;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;
import es.ediae.master.programacion.gestionusuario.repository.IDireccionRepository;
import es.ediae.master.programacion.gestionusuario.repository.IGeneroRepository;
import es.ediae.master.programacion.gestionusuario.repository.IPuestoTrabajoRepository;
import es.ediae.master.programacion.gestionusuario.repository.IUsuarioRepository;

@Component
@Profile("dev")
public class DataSeeder implements CommandLineRunner {

    private final IDireccionRepository direccionRepo;
    private final IUsuarioRepository usuarioRepo;
    private final IGeneroRepository generoRepo;
    private final IPuestoTrabajoRepository puestoRepo;

    public DataSeeder(
        IDireccionRepository direccionRepo,
        IUsuarioRepository usuarioRepo,
        IGeneroRepository generoRepo,
        IPuestoTrabajoRepository puestoRepo
    ) {
        this.direccionRepo = direccionRepo;
        this.usuarioRepo = usuarioRepo;
        this.generoRepo = generoRepo;
        this.puestoRepo = puestoRepo;
    }

    @Override
    public void run(String... args) {
        // -- Vider dans l'ordre FK (dependant d'abord)
        direccionRepo.deleteAll();
        usuarioRepo.deleteAll();
        generoRepo.deleteAll();
        puestoRepo.deleteAll();

        // -- Generos
        GeneroEntity masculino = generoRepo.save(new GeneroEntity(null, "Masculino"));
        GeneroEntity femenino  = generoRepo.save(new GeneroEntity(null, "Femenino"));
        GeneroEntity otro      = generoRepo.save(new GeneroEntity(null, "Otro"));

        // -- Puestos de trabajo
        PuestoDeTrabajoEntity dev     = puestoRepo.save(new PuestoDeTrabajoEntity(null, "Desarrollador"));
        PuestoDeTrabajoEntity qa      = puestoRepo.save(new PuestoDeTrabajoEntity(null, "QA"));
        PuestoDeTrabajoEntity manager = puestoRepo.save(new PuestoDeTrabajoEntity(null, "Manager"));

        // -- Usuarios
        UsuarioEntity alice = usuarioRepo.save(new UsuarioEntity(
            "alice", "pass1234", LocalDateTime.now(),
            femenino, "Alice", "García", "López",
            LocalDate.of(1992, 3, 15), LocalTime.of(8, 0),
            true, dev, null
        ));

        UsuarioEntity bob = usuarioRepo.save(new UsuarioEntity(
            "bob", "pass5678", LocalDateTime.now(),
            masculino, "Bob", "Martínez", "Sánchez",
            LocalDate.of(1988, 7, 22), LocalTime.of(7, 30),
            false, qa, null
        ));

        UsuarioEntity carol = usuarioRepo.save(new UsuarioEntity(
            "carol", "pass9999", LocalDateTime.now(),
            femenino, "Carol", "Ruiz", null,
            LocalDate.of(1995, 11, 5), LocalTime.of(9, 0),
            false, manager, null
        ));

        // -- Direcciones
        DireccionEntity d1 = new DireccionEntity(null, "Calle Mayor", "12", true);
        d1.setUsuario(alice);

        DireccionEntity d2 = new DireccionEntity(null, "Avenida del Sol", "3B", false);
        d2.setUsuario(alice);

        DireccionEntity d3 = new DireccionEntity(null, "Plaza España", "1", true);
        d3.setUsuario(bob);

        DireccionEntity d4 = new DireccionEntity(null, "Calle Luna", "7", true);
        d4.setUsuario(carol);

        direccionRepo.saveAll(List.of(d1, d2, d3, d4));

        System.out.println("[DataSeeder] Dataset chargé : "
            + usuarioRepo.count() + " usuarios, "
            + direccionRepo.count() + " direcciones.");
    }
}
