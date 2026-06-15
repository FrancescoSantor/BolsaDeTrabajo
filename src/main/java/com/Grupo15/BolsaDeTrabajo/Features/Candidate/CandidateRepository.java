package com.Grupo15.BolsaDeTrabajo.Features.Candidate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidatesEntity,Long> {

    boolean existsByEmail(String email);


}
