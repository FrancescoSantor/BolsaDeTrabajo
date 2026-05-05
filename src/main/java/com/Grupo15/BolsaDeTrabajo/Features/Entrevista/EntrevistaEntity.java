package com.Grupo15.BolsaDeTrabajo.Features.Entrevista;

import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulacionEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.*;

@Entity
@Table(name = "entrevista")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntrevistaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "postulacion_id")
    private PostulacionEntity postulacion;

    private LocalDateTime fecha;

    //ENUM DE TIPO (VIRTUAL/PRECENCIAL/ETC)
    @Enumerated(EnumType.STRING)
    private Type tipo;

    //NO NECESARIO SI ES PRESCENCIAL
    private String linkReunion;

    @Column(columnDefinition = "TEXT")
    private String notasEmpresa;

    @Column(columnDefinition = "TEXT")
    private String feedbackCandidato;

    //ENUM DE ESTADO
    @Enumerated(EnumType.STRING)
    private InterviewState estado;
}