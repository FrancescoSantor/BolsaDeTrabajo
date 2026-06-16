package com.Grupo15.BolsaDeTrabajo.Features.Following.dto;

public record FollowingsRequestDTO(
        Long followerId,
        Long followedId
) {}