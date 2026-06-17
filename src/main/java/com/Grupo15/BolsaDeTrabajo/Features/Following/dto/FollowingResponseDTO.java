package com.Grupo15.BolsaDeTrabajo.Features.Following.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Following.FollowState;

import java.time.LocalDateTime;
import java.util.UUID;

public record FollowingResponseDTO(
        UUID externalId,
        String userName,          // viene de user.name
        String companyName,      // viene de company.registeredName
        //String userFollowing, ??
        FollowState state,
        LocalDateTime createdAt) {
}
