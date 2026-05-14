package com.Grupo15.BolsaDeTrabajo.Features.Publicaciones.dto;

import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import java.util.UUID;

@Builder
public class PostsResponseDTO {
    private UUID externalId;
    private String companyName;     // viene de company.registeredName
    private String companyLocation; // viene de company.location
    private String offerTitle;      // viene de offer.title
    private String title;
    private String content;
    private String urlImage;
    private int totalLikes;
    private int totalComments;
    private boolean active;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}