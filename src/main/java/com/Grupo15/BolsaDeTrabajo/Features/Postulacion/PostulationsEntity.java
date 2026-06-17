package com.Grupo15.BolsaDeTrabajo.Features.Postulacion;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Interview.InterviewEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidatesEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidato_id")
    private CandidatesEntity candidate;

    //solucion de mapeado a OfertaEntity
    @ManyToOne(fetch = FetchType.LAZY)
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

    @PrePersist
    protected void onCreate(){
        this.postulationDate = Timestamp.from(Instant.now());
    }

    @PreUpdate
    protected void onUpdate(){
        this.updateDate = Timestamp.from(Instant.now());
    }


}


