package com.Grupo15.BolsaDeTrabajo.Features.Entrevista;

import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulacionEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "entrevista")
public class EntrevistaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "postulacion_id")
    private PostulacionEntity postulacion;

    private LocalDateTime fecha;

    //ENUM DE TIPO (VIRTUAL/PRECENCIAL/ETC)
    private String tipo;

    //NO NECESARIO SI ES PRESCENCIAL
    private String linkReunion;

    @Column(columnDefinition = "TEXT")
    private String notasEmpresa;

    @Column(columnDefinition = "TEXT")
    private String feedbackCandidato;

    //ENUM DE ESTADO
    private String estado;
}