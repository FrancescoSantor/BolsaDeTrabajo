package com.Grupo15.BolsaDeTrabajo.Features.CandidatoHabilidad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateHabilityRepository extends JpaRepository<CandidatoHabilidadEntity,Long> {
}
