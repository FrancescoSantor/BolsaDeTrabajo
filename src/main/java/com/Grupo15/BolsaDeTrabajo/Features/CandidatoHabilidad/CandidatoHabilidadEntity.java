package com.Grupo15.BolsaDeTrabajo.Features.CandidatoHabilidad;

import com.Grupo15.BolsaDeTrabajo.Features.Habilidad.HabilidadEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.CandidatosEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

public class CandidatoHabilidadEntity {

    @Entity
    @Table(name = "candidato_habilidad")
    @Data
    @NoArgsConstructor
    public class CandidatoHabilidad {

        @EmbeddedId
        private CandidatoHabilidadId id;

        @ManyToOne
        @MapsId("candidatoId")
        @JoinColumn(name = "candidato_id")
        private CandidatosEntity candidato;

        @ManyToOne
        @MapsId("habilidadId")
        @JoinColumn(name = "habilidad_id")
        //private HabilidadEntity.Habilidad habilidad;
        private HabilidadEntity habilidad;


        private String nivel;
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    public class CandidatoHabilidadId implements Serializable {
        private Long candidatoId;
        private Long habilidadId;
    }

}
