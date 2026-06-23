package com.Grupo15.BolsaDeTrabajo.Features.Postulacion;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidatesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostulationRepository extends JpaRepository<PostulationsEntity,Long> {

    boolean existsByCandidateAndOffer (CandidatesEntity candidate, OfferEntity offer);

    Optional<PostulationsEntity> findByExternalId(UUID externalId);

    List<PostulationsEntity> findByCandidateExternalId(UUID candidateExternalId);

    @Query("SELECT p FROM PostulationsEntity p " +
    "WHERE (:CandidateId IS NULL OR p.candidate.externalId = :CandidateId) " +
    "AND (:OfferId IS NULL OR p.offer.externalId = :OfferId) "+
    "AND (:Status IS NULL OR p.status = :Status) " +
    "ORDER BY p.postulationDate DESC")
    List<PostulationsEntity> findPostulationsWithFilters (
      @Param("CandidateId") UUID candidateId,
      @Param("OfferId") UUID offerId,
      @Param("Status") PostulationState status
    );



}