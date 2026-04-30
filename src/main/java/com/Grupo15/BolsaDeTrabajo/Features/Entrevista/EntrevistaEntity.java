package com.Grupo15.BolsaDeTrabajo.Features.Entrevista;

import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulacionEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


public class EntrevistaEntity {

    @Entity
    @Table(name = "entrevista")
    @Data
    @NoArgsConstructor
    public class Entrevista {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne
        @JoinColumn(name = "postulacion_id")
        private PostulacionEntity.Postulacion postulacion;

        private LocalDateTime fechaHora;

        private String tipo;
        private String linkReunion;

        @Column(columnDefinition = "TEXT")
        private String notasEmpresa;

        @Column(columnDefinition = "TEXT")
        private String feedbackCandidato;

        private String estado;
    }

}
