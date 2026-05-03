package es.ediae.master.programacion.gestionusuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;

public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    UsuarioEntity findByNickUsuario(String nickUsuario);

}
