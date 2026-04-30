package com.Grupo15.BolsaDeTrabajo.Features.OfertaHabilidad;

import com.Grupo15.BolsaDeTrabajo.Features.Habilidad.HabilidadEntity;
import com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.OfertaLaboralEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


public class OfertaHabilidadEntity {

    @Entity
    @Table(name = "oferta_habilidad")
    @Data
    @NoArgsConstructor
    public class OfertaHabilidad {

        @EmbeddedId
        private OfertaHabilidadId id;

        @ManyToOne
        @MapsId("ofertaId")
        @JoinColumn(name = "oferta_id")
        private OfertaLaboralEntity oferta;

        @ManyToOne
        @MapsId("habilidadId")
        @JoinColumn(name = "habilidad_id")
        private HabilidadEntity habilidad;

        private boolean requerida;
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    public class OfertaHabilidadId implements Serializable {
        private Long ofertaId;
        private Long habilidadId;
    }

}
