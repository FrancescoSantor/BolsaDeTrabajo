package com.Grupo15.BolsaDeTrabajo.Features.PublicacionesLikes.dto;

public record PostLikesRequestDTO(
        Long userId,
        Long postId
) {}