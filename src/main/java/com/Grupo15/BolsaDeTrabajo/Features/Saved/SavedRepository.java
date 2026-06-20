package com.Grupo15.BolsaDeTrabajo.Features.Saved;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedRepository extends JpaRepository<SavedEntity,Long> {

    boolean existsByCandidateIdAndOfferId(Long candidateId, Long offerId);

    boolean existsByCompanyIdAndCandidateId(Long companyId, Long candidateId);

    List<SavedEntity> findByCandidateId(Long candidateId);
}
