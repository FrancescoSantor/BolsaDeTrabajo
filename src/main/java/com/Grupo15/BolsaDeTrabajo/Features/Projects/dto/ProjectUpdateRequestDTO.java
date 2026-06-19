package com.Grupo15.BolsaDeTrabajo.Features.Projects.dto;

import java.sql.Timestamp;

public record ProjectUpdateRequestDTO(
        String projectName,
        String description,
        Timestamp initialDate,
        Timestamp endDate,
        String urlLink
) {
}
