package com.Grupo15.BolsaDeTrabajo.Features.Saved.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record SavedCandidateResponseDTO(
        UUID externalId,
        UUID candidateExternalId,
        String candidateName,
        String candidateLastName,
        Timestamp createdAt
) {}