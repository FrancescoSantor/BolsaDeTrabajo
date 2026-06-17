package com.Grupo15.BolsaDeTrabajo.Features.Postulacion;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidatesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostulationRepository extends JpaRepository<PostulationsEntity,Long> {

    boolean existsByCandidateAndOffer (CandidatesEntity candidate, OfferEntity offer);


}