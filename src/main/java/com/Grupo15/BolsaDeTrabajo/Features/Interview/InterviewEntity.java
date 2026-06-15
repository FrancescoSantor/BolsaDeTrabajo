package com.Grupo15.BolsaDeTrabajo.Features.Interview;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulationsEntity;
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
public class InterviewEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "postulacion_id")
    private PostulationsEntity application;


    private LocalDateTime date;


    //ENUM DE TIPO (VIRTUAL/PRECENCIAL/ETC)
    @Enumerated(EnumType.STRING)
    private Type type;

    //NO NECESARIO SI ES PRESCENCIAL
    private String linkMeeting;

    @Column(columnDefinition = "TEXT")
    private String companyNotes;

    @Column(columnDefinition = "TEXT")
    private String feedbackCandidate;

    //ENUM DE ESTADO
    @Enumerated(EnumType.STRING)
    private InterviewStatus status;
}