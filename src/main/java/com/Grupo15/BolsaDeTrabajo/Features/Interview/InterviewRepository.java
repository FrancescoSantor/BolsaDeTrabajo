package com.Grupo15.BolsaDeTrabajo.Features.Interview;

import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InterviewRepository extends JpaRepository<InterviewEntity, Long> {

    boolean existsByApplicationExternalId(UUID externalId);
    Optional<InterviewEntity> findByExternalId (UUID externalId);
}
