package com.Grupo15.BolsaDeTrabajo.Features.Guardados;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.CandidatosEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.EmpresasEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


public class GuardadosEntity {

    @Entity
    @Table(name = "guardados")
    @Data
    @NoArgsConstructor
    public class Guardado {

        @EmbeddedId
        private GuardadoId id;

        @ManyToOne
        @MapsId("candidatoId")
        private CandidatosEntity candidato;

        @ManyToOne
        @MapsId("ofertaId")
        private EmpresasEntity oferta; // (ojo: tu DER dice empresa, raro acá)

        private Timestamp createdAt;
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    public class GuardadoId implements Serializable {
        private Long candidatoId;
        private Long ofertaId;
    }

}
