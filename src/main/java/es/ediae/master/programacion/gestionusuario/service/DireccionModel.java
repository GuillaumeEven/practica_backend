package es.ediae.master.programacion.gestionusuario.service;

import es.ediae.master.programacion.gestionusuario.dtos.DireccionDTO;
import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;

public class DireccionModel {

        private Integer id;
        private String nombre_calle;
        private String numero_calle;
        private boolean direccion_principal;
        private Integer usuario_id;

        public DireccionModel() {
        }

        public DireccionModel(Integer id, String nombre_calle, String numero_calle, boolean direccion_principal, Integer usuario_id) {
                this.id = id;
                this.nombre_calle = nombre_calle;
                this.numero_calle = numero_calle;
                this.direccion_principal = direccion_principal;
                this.usuario_id = usuario_id;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getNombre_calle() {
                return nombre_calle;
        }


        public void setNombre_calle(String nombre_calle) {
                this.nombre_calle = nombre_calle;
        }

        public String getNumero_calle() {
                return numero_calle;
        }

        public void setNumero_calle(String numero_calle) {
                this.numero_calle = numero_calle;
        }

        public boolean isDireccion_principal() {
                return direccion_principal;
        }

        public void setDireccion_principal(boolean direccion_principal) {
                this.direccion_principal = direccion_principal;
        }

        public Integer getUsuario_id() {
                return usuario_id;
        }

        public void setUsuario_id(Integer usuario_id) {
                this.usuario_id = usuario_id;
        }

        public static DireccionModel fromEntity(es.ediae.master.programacion.gestionusuario.entity.DireccionEntity entity) {
                return new DireccionModel(
                        entity.getId(),
                        entity.getNombre_calle(),
                        entity.getNumero_calle(),
                        entity.isDireccion_principal(),
                        entity.getUsuario() != null ? entity.getUsuario().getId() : null
                );
        }

        public DireccionEntity toEntity() {
                return new DireccionEntity(
                        this.id,
                        this.nombre_calle,
                        this.numero_calle,
                        this.direccion_principal
                );
        }

        public DireccionDTO toDTO() {
                return new DireccionDTO(
                        this.id,
                        this.nombre_calle,
                        this.numero_calle,
                        this.direccion_principal,
                        this.usuario_id
                );
        }
}
