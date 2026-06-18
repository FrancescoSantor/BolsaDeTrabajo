package com.Grupo15.BolsaDeTrabajo.Features.Candidate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidatesEntity,Long> {

    boolean existsByEmail(String email);

    Optional<CandidatesEntity> findByExternalId(UUID externalId);


}
