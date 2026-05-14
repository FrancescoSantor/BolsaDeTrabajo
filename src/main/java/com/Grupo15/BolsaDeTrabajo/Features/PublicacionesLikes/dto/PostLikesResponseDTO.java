package com.Grupo15.BolsaDeTrabajo.Features.PublicacionesLikes.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class PostLikesResponseDTO {
    private UUID externalId;
    private String userName;      // viene de user.name
    private String userLastName;  // viene de user.lastName
    private String postTitle;     // viene de post.title
    private Timestamp createdAt;
}