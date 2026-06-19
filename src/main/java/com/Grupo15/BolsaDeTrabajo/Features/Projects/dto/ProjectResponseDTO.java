package com.Grupo15.BolsaDeTrabajo.Features.Projects.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record ProjectResponseDTO(
        UUID externalId,
        UUID candidateId,
        String projectName,
        String description,
        Timestamp initialDate,
        Timestamp endDate,
        String urlLink
) {
}
