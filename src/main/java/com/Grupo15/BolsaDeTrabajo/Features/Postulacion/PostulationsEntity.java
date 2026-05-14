package com.Grupo15.BolsaDeTrabajo.Features.Postulacion;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Entrevista.InterviewEntity;
import com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.OfferEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.CandidatesEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "postulacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostulationsEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //solucion de mapeado a CandidatosEntity
    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private CandidatesEntity candidate;

    //solucion de mapeado a OfertaEntity
    @ManyToOne
    @JoinColumn(name = "oferta_id")
    private OfferEntity offer;

    //ENUM DE ESTADO DE LA OFERTA
    @Enumerated(EnumType.STRING)
    private PostulationState status;

    @Column(columnDefinition = "TEXT")
    private String coverLetter;

    private Timestamp postulationDate;
    private Timestamp updateDate;


    @OneToOne(mappedBy = "application")
    private InterviewEntity interview;
}


