package com.Grupo15.BolsaDeTrabajo.Features.PostLikes.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PostLikesRequestDTO(
        @NotNull(message = "El ID del usuario es obligatorio.")
        Long userId,

        @NotNull(message = "El externalId del post es obligatorio.")
        //Long postId
        UUID postExternalId
) {}