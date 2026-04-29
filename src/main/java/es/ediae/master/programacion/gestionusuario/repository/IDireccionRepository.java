package es.ediae.master.programacion.gestionusuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;

@Repository
public interface IDireccionRepository extends JpaRepository<DireccionEntity, Object> {

    @Query("SELECT d FROM DireccionEntity d WHERE d.usuario.id = :usuarioId")
    List<DireccionEntity> findByUsuarioId(Integer usuarioId);
}
