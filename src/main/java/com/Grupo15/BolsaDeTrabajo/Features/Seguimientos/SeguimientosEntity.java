package com.Grupo15.BolsaDeTrabajo.Features.Seguimientos;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.EmpresasEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


public class SeguimientosEntity {

    @Entity
    @Table(name = "seguimientos")
    @Data
    @NoArgsConstructor
    public class Seguimiento {

        @EmbeddedId
        private SeguimientoId id;

        @ManyToOne
        @MapsId("usuarioId")
        private UsersEntity usuario;

        @ManyToOne
        @MapsId("empresaId")
        private EmpresasEntity empresa;

        private Timestamp createdAt;
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    public class SeguimientoId implements Serializable {
        private Long usuarioId;
        private Long empresaId;
    }

}
