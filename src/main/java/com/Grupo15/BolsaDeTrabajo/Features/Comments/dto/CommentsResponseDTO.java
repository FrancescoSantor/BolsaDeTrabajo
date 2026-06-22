package com.Grupo15.BolsaDeTrabajo.Features.Comments.dto;

import java.sql.Timestamp;

public record CommentsResponseDTO(
        String UserName,
        String content,
        Timestamp createdAt
) {
}
