package com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExperienceRepository extends JpaRepository<LaboralExperienceEntity,Long> {

    // Busca una experiencia laboral específica usando el UUID seguro
    Optional<LaboralExperienceEntity> findByExternalId(UUID externalId);

    // Trae la lista completa de experiencias de un candidato usando su ID numérico
    List<LaboralExperienceEntity> findAllByCandidateId(Long candidateId);
}

