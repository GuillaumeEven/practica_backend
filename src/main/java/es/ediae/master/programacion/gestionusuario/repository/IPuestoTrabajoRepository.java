package es.ediae.master.programacion.gestionusuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ediae.master.programacion.gestionusuario.entity.PuestoDeTrabajoEntity;

public interface IPuestoTrabajoRepository extends JpaRepository<PuestoDeTrabajoEntity, Integer> {

}
