package com.Grupo15.BolsaDeTrabajo.Features.Post.dto;

public record PostsRequestDTO(
        Long companyId,
        Long offerId,
        String title,
        String content,
        String urlImage
) {}