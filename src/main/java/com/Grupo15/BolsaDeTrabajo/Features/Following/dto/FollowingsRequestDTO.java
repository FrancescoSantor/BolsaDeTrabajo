package com.Grupo15.BolsaDeTrabajo.Features.Following.dto;

import java.util.UUID;

public record FollowingsRequestDTO(
        UUID followerId,
        UUID followedId
) {}