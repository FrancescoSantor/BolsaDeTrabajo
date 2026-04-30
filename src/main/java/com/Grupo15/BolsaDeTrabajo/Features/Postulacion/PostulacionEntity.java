package com.Grupo15.BolsaDeTrabajo.Features.Postulacion;

import com.Grupo15.BolsaDeTrabajo.Features.Entrevista.EntrevistaEntity;
import com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.OfertaLaboralEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.CandidatosEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


public class PostulacionEntity {

    @Entity
    @Table(name = "postulacion")
    @Data
    @NoArgsConstructor
    public class Postulacion {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private CandidatosEntity candidato;

        @ManyToOne
        private OfertaLaboralEntity oferta;

        private String estado;

        @Column(columnDefinition = "TEXT")
        private String cartaPresentacion;

        private Timestamp fechaPostulacion;
        private Timestamp fechaActualizacion;

        @OneToOne(mappedBy = "postulacion")
        private EntrevistaEntity entrevista;
    }

}
