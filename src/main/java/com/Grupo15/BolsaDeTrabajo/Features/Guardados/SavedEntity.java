package com.Grupo15.BolsaDeTrabajo.Features.Guardados;

import com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.OfferEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.CandidatesEntity;
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
public class SavedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private CandidatesEntity candidate;

    @ManyToOne
    @JoinColumn(name = "oferta_id")
    private OfferEntity offer;

    private Timestamp createdAt;
}