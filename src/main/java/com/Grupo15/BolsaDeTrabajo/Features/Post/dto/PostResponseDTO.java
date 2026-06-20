package com.Grupo15.BolsaDeTrabajo.Features.Post.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record PostResponseDTO(
        UUID externalId,
        String companyName,    // viene de company.registeredName
        String offerTitle,      // viene de offer.title
        String title,
        String content,
        Integer totalLikes,
        Integer totalComments,
        Boolean active,
        Timestamp createdAt,
        Timestamp updatedAt
) {
}
