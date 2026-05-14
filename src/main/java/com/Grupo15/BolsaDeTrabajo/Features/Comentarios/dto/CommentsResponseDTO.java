package com.Grupo15.BolsaDeTrabajo.Features.Comentarios.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.UUID;
@Getter
@Builder
public class CommentsResponseDTO {
    private UUID externalId;
    private String userName;
    private String userLastName;
    private String content;
    private Timestamp createdAt;
}
