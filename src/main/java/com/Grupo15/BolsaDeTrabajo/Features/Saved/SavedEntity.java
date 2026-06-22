package com.Grupo15.BolsaDeTrabajo.Features.Saved;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidatesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Company.CompaniesEntity;
import jakarta.persistence.*;

import java.sql.Timestamp;

import lombok.*;

@Entity
@Table(name = "guardados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private CandidatesEntity candidate;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompaniesEntity company;

    @ManyToOne
    @JoinColumn(name = "oferta_id")
    private OfferEntity offer;

    private Timestamp createdAt;
}