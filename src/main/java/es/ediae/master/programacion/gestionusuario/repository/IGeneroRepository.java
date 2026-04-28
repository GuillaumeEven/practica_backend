package es.ediae.master.programacion.gestionusuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ediae.master.programacion.gestionusuario.entity.GeneroEntiity;

public interface IGeneroRepository extends JpaRepository<GeneroEntiity, Long> {

}
