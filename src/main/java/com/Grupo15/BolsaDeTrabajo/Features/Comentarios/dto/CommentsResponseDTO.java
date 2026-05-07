package com.Grupo15.BolsaDeTrabajo.Features.Comentarios.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class CommentsResponseDTO {
    private UUID externalId;
    private String userName;
    private String userLastName;
    private String content;
    private Timestamp createdAt;
}
