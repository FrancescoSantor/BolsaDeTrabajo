package com.Grupo15.BolsaDeTrabajo.Features.Comentarios.dto;


public record CommentsRequestDTO(
        Long postId,
        Long userId,
        String content
) {}