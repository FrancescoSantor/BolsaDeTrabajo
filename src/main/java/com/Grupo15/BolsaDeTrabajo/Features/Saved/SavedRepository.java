package com.Grupo15.BolsaDeTrabajo.Features.Saved;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SavedRepository extends JpaRepository<SavedEntity,Long> {

    boolean existsByCandidateIdAndOfferId(UUID candidateId, Long offerId);

    boolean existsByCompanyIdAndCandidateId(Long companyId, UUID candidateId);

    List<SavedEntity> findByCandidateId(Long candidateId);
}
