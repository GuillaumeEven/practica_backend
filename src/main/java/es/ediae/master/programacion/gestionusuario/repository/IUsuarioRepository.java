package es.ediae.master.programacion.gestionusuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;

public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    @NativeQuery("SELECT * FROM usuarios WHERE nick_usuario = :nickUsuario")
    UsuarioEntity findByNickUsuario(String nickUsuario);

}
