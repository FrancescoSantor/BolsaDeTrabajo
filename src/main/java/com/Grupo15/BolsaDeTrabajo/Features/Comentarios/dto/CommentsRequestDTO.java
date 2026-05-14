package com.Grupo15.BolsaDeTrabajo.Features.Comentarios.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentsRequestDTO{
    Long postId;
    Long userId;
    String content;
}