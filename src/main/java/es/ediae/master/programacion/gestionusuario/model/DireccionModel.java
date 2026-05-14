package es.ediae.master.programacion.gestionusuario.model;

import es.ediae.master.programacion.gestionusuario.dtos.DireccionDTO;
import es.ediae.master.programacion.gestionusuario.dtos.DireccionPostDTO;
import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;

public class DireccionModel {

        private Integer id;
        private String nombreCalle;
        private String numeroCalle;
        private boolean direccionPrincipal;
        private Integer usuarioId;

        public DireccionModel() {
        }

        public DireccionModel(Integer id, String nombreCalle, String numeroCalle, boolean direccionPrincipal, Integer usuarioId) {
                this.id = id;
                this.nombreCalle = nombreCalle;
                this.numeroCalle = numeroCalle;
                this.direccionPrincipal = direccionPrincipal;
                this.usuarioId = usuarioId;
        }

        public DireccionModel(String nombreCalle, String numeroCalle, boolean direccionPrincipal, Integer usuarioId) {
                this.nombreCalle = nombreCalle;
                this.numeroCalle = numeroCalle;
                this.direccionPrincipal = direccionPrincipal;
                this.usuarioId = usuarioId;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getNombreCalle() {
                return nombreCalle;
        }


        public void setNombreCalle(String nombreCalle) {
                this.nombreCalle = nombreCalle;
        }

        public String getNumeroCalle() {
                return numeroCalle;
        }

        public void setNumeroCalle(String numeroCalle) {
                this.numeroCalle = numeroCalle;
        }

        public boolean isDireccionPrincipal() {
                return direccionPrincipal;
        }

        public void setDireccionPrincipal(boolean direccionPrincipal) {
                this.direccionPrincipal = direccionPrincipal;
        }

        public Integer getUsuarioId() {
                return usuarioId;
        }

        public void setUsuarioId(Integer usuarioId) {
                this.usuarioId = usuarioId;
        }

        public static DireccionModel fromEntity(DireccionEntity entity) {
                return new DireccionModel(
                        entity.getId(),
                        entity.getNombreCalle(),
                        entity.getNumeroCalle(),
                        entity.isDireccionPrincipal(),
                        entity.getUsuario() != null ? entity.getUsuario().getId() : null
                );
        }

        public DireccionDTO toGetDTO() {
                return new DireccionDTO(
                        this.id,
                        this.nombreCalle,
                        this.numeroCalle,
                        this.direccionPrincipal,
                        this.usuarioId
                );
        }

        public static DireccionModel fromDTO(DireccionDTO dto) {

                return new DireccionModel(
                        dto.getId(),
                        dto.getNombreCalle(),
                        dto.getNumeroCalle(),
                        dto.isDireccionPrincipal(),
                        dto.getUsuarioId()
                );
        }

        public static DireccionModel fromPostDTO(DireccionPostDTO dto) {
                if (dto == null) return null;
                return new DireccionModel(
                        dto.getNombreCalle(),
                        dto.getNumeroCalle(),
                        dto.isDireccionPrincipal() != null ? dto.isDireccionPrincipal() : false,
                        dto.getUsuarioId()
                );
        }

        public DireccionEntity toEntity() {
                return new DireccionEntity(
                        this.id,
                        this.nombreCalle,
                        this.numeroCalle,
                        this.direccionPrincipal
                );
        }

}
