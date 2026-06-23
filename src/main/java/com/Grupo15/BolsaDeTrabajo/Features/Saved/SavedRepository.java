package com.Grupo15.BolsaDeTrabajo.Features.Saved;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SavedRepository extends JpaRepository<SavedEntity,Long> {

    boolean existsByCandidateExternalIdAndOfferExternalId(UUID candidateId, UUID offerId);

    boolean existsByCompanyExternalIdAndCandidateExternalId(UUID companyId, UUID candidateId);

    //List<SavedEntity> findByCandidateId(Long candidateId);
}
