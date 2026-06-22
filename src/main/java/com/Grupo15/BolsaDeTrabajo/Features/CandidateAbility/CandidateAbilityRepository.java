package com.Grupo15.BolsaDeTrabajo.Features.CandidateAbility;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateAbilityRepository extends JpaRepository<CandidateAbilityEntity,Long> {
}
