package com.Grupo15.BolsaDeTrabajo.Features.Post.dto;

import java.util.UUID;

public record PostsRequestDTO(
        UUID companyId,
        UUID offerId,
        String title,
        String content,
        String urlImage
) {}